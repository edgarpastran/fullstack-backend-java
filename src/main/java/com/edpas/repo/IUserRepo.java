package com.edpas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edpas.model.User;

public interface IUserRepo extends JpaRepository<User, Integer>{

	public User findOneByUsername(String username);
}
