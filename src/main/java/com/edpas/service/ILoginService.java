package com.edpas.service;

import com.edpas.model.User;

public interface ILoginService {

	public User checkUsername(String username);
	
	public int changePassword(String password, String username);
}
