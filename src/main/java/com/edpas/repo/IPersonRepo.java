package com.edpas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edpas.model.Person;

public interface IPersonRepo extends JpaRepository<Person, Integer> {

}
