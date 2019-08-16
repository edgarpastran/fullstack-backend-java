package com.edpas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edpas.model.Person;
import com.edpas.repo.IPersonRepo;
import com.edpas.service.IPersonService;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepo personRepo;
	
	@Override
	public Person insert(Person object) {
		return this.personRepo.save(object);
	}

	@Override
	public Person update(Person object) {
		return this.personRepo.save(object);
	}

	@Override
	public void delete(Integer id) {
		this.personRepo.delete(id);
	}

	@Override
	public Person getOne(Integer id) {
		return this.personRepo.findOne(id);
	}

	@Override
	public List<Person> getAll() {
		return this.personRepo.findAll();
	}

}
