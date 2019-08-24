package com.edpas;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.edpas.model.Role;
import com.edpas.model.User;
import com.edpas.repo.IRoleRepo;
import com.edpas.repo.IUserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EdpasBackendApplicationTests {

	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private IRoleRepo roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	public void createUserAdministrator() {
		User user = new User();
		user.setIdUser(1);
		user.setUsername("admin-edpas@dispostable.com");
		user.setPassword(this.bcrypt.encode("123"));
		user.setEnabled(true);
		//Role role = this.roleRepo.findOneByName("Administrator");
		Role role = this.roleRepo.getOne(1);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		
		User result = this.userRepo.save(user);
		assertTrue(result.getUsername().equals(user.getUsername()));
	}
	
	@Test
	public void createUserManager() {
		User user = new User();
		user.setIdUser(2);
		user.setUsername("manager-edpas@dispostable.com");
		user.setPassword(this.bcrypt.encode("123"));
		user.setEnabled(true);
		Role role = this.roleRepo.findOneByName("Manager");
		//Role role = this.roleRepo.getOne(2);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		
		User result = this.userRepo.save(user);
		assertTrue(result.getUsername().equals(user.getUsername()));
	}
	
	@Test
	public void createUserOperator() {
		User user = new User();
		user.setIdUser(3);
		user.setUsername("operator-edpas@dispostable.com");
		user.setPassword(this.bcrypt.encode("123"));
		user.setEnabled(true);
		//Role role = this.roleRepo.findOneByName("Operator");
		Role role = this.roleRepo.getOne(3);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		
		User result = this.userRepo.save(user);
		assertTrue(result.getUsername().equals(user.getUsername()));
	}

}
