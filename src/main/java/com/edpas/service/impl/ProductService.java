package com.edpas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edpas.model.Product;
import com.edpas.repo.IProductRepo;
import com.edpas.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public Product insert(Product object) {
		return this.productRepo.save(object);
	}

	@Override
	public Product update(Product object) {
		return this.productRepo.save(object);
	}

	@Override
	public void delete(Integer id) {
		this.productRepo.delete(id);
	}

	@Override
	public Product getOne(Integer id) {
		return this.productRepo.findOne(id);
	}

	@Override
	public List<Product> getAll() {
		return this.productRepo.findAll();
	}

}
