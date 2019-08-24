package com.edpas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edpas.exception.ModelNotFoundException;
import com.edpas.model.Person;
import com.edpas.service.IPersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private IPersonService personService;

	@GetMapping
	public ResponseEntity<List<Person>> list() {
		List<Person> list = this.personService.getAll();
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Person>> listPageable(Pageable pageable) {
		Page<Person> list = this.personService.listPageable(pageable);
		return new ResponseEntity<Page<Person>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> find(@PathVariable("id") Integer id) {
		Person object = this.personService.getOne(id);
		if (object == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		return new ResponseEntity<Person>(object, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> register(@Valid @RequestBody Person person) {
		Person saved = this.personService.insert(person);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdPerson()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@Valid @RequestBody Person person) {
		this.personService.update(person);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		Person object = this.personService.getOne(id);
		if (object == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		else {
			this.personService.delete(id);
		}
		return new ResponseEntity<Object>(object, HttpStatus.OK);
	}
}
