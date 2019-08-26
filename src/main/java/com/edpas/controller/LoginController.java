package com.edpas.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edpas.model.ResetToken;
import com.edpas.model.User;
import com.edpas.service.ILoginService;
import com.edpas.service.IResetTokenService;
import com.edpas.util.EmailService;
import com.edpas.util.Mail;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IResetTokenService resetTokenService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Value("${edpas.password.recuperate.email}")
	private String emailFrom;
	
	@Value("${edpas.password.recuperate.host}")
	private String publicHost;
	
	
	@PostMapping(value = "/sendEmail", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Integer> sendEmail(@RequestBody String email) {
		int result = 0;
		try {
			User user = this.loginService.checkUsername(email);
			if (user != null && user.getIdUser() > 0) {
			
				ResetToken token = new ResetToken();
				token.setToken(UUID.randomUUID().toString());
				token.setUser(user);
				token.setExpiration(10);
				this.resetTokenService.save(token);
				
				System.out.println("FROM: "+this.emailFrom);
				Mail mail = new Mail();
				mail.setFrom(this.emailFrom);
				mail.setTo(user.getUsername());
				mail.setSubject("RESET PASSWORD - EdPas");
				
				Map<String, Object> model = new HashMap<>();
				String url = this.publicHost+"/recover/" + token.getToken();
				System.out.println("URL: "+url);
				model.put("user", token.getUser().getUsername());
				model.put("resetUrl", url);
				mail.setModel(model);
				this.emailService.sendEmail(mail);
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
		
	@GetMapping(value = "/reset/check/{token}")
	public ResponseEntity<Integer> checkToken(@PathVariable("token") String token) {
		int result = 0;
		try {
			if (token != null && !token.isEmpty()) {
				ResetToken resetToken = this.resetTokenService.findByToken(token);
				if (resetToken != null && resetToken.getId() > 0) {
					if (!resetToken.isExpired()) {
						result = 1;
					}
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/reset/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> resetPassword(@PathVariable("token") String token, @RequestBody String password) {
		int result = 0;
		try {
			ResetToken resetToken = this.resetTokenService.findByToken(token);
			String passwordHash = bcrypt.encode(password);
			result = this.loginService.changePassword(passwordHash, resetToken.getUser().getUsername());
			this.resetTokenService.delete(resetToken);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
