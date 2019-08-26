package com.edpas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edpas.model.User;
import com.edpas.repo.IUserRepo;
import com.edpas.service.ILoginService;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public User checkUsername(String username) {
		User user = null;
		try {
			user = this.userRepo.findOneByUsername(username);
			user = user != null ? user : new User();
		} 
		catch (Exception e) {
			user = new User();
		}
		return user;
	}

	@Override
	public int changePassword(String password, String username) {
		int result = 0;
		try {
			this.userRepo.changePassword(password, username);
			result = 1;
		} 
		catch (Exception e) {
			result = 0;
		}
		return result;
	}

}
