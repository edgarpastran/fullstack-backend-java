package com.edpas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICRUDService<T> {

	public T insert(T t);
	
	public T update(T t);
	
	public void delete(Integer id);
	
	public T getOne(Integer id);
	
	public List<T> getAll();
	
	public Page<T> listPageable(Pageable pageable);
}
