package com.edpas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.edpas.model.Person;
import com.edpas.repo.IPersonRepo;
import com.edpas.service.IPersonService;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepo personRepo;
	
	@PreAuthorize("@restAuthService.hasAccess('person.insert')")
	@Override
	public Person insert(Person object) {
		return this.personRepo.save(object);
	}

	@PreAuthorize("@restAuthService.hasAccess('person.update')")
	@Override
	public Person update(Person object) {
		return this.personRepo.save(object);
	}

	@PreAuthorize("@restAuthService.hasAccess('person.delete')")
	@Override
	public void delete(Integer id) {
		this.personRepo.delete(id);
	}

	@PreAuthorize("@restAuthService.hasAccess('person.getOne')")
	@Override
	public Person getOne(Integer id) {
		return this.personRepo.findOne(id);
	}

	@PreAuthorize("@restAuthService.hasAccess('person.getAll')")
	@Override
	public List<Person> getAll() {
		return this.personRepo.findAll();
	}

	@PreAuthorize("@restAuthService.hasAccess('person.listPageable')")
	@Override
	public Page<Person> listPageable(Pageable pageable) {		
		return this.personRepo.findAll(pageable);
	}

}
