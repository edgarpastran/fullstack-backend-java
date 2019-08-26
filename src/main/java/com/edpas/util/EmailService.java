package com.edpas.util;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendEmail(Mail mail) {
		try {
			MimeMessage message = this.emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Context context = new Context();
			context.setVariables(mail.getModel());
			
			String html = this.templateEngine.process("email/email-template", context);
			
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			
			this.emailSender.send(message);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
