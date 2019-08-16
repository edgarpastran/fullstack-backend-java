package com.edpas.service;

import java.util.List;

import com.edpas.model.Person;

public interface ICRUDService<T> {

	public T insert(T t);
	
	public T update(T t);
	
	public void delete(Integer id);
	
	public T getOne(Integer id);
	
	public List<T> getAll();
}
