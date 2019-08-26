package com.edpas.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edpas.model.User;

public interface IUserRepo extends JpaRepository<User, Integer>{

	public User findOneByUsername(String username);		
	
	@Transactional
	@Modifying
	@Query("UPDATE User user SET user.password = :password WHERE user.username = :username")
	public void changePassword(@Param("password") String password, @Param("username") String username) throws Exception;
}
