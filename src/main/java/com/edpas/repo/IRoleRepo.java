package com.edpas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edpas.model.Role;

public interface IRoleRepo extends JpaRepository<Role, Integer>{

	public Role findOneByName(String name);
}
