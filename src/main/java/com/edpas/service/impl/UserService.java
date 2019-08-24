package com.edpas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edpas.model.User;
import com.edpas.repo.IUserRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private IUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findOneByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User doesn't exists", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		user.getRoles().forEach(role -> {
			roles.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
		return userDetails;
	}

}
