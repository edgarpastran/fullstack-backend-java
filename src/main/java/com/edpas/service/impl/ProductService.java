package com.edpas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.edpas.model.Product;
import com.edpas.repo.IProductRepo;
import com.edpas.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepo productRepo;
	
	@PreAuthorize("@restAuthService.hasAccess('product.insert')")
	@Override
	public Product insert(Product object) {
		return this.productRepo.save(object);
	}

	@PreAuthorize("@restAuthService.hasAccess('product.update')")
	@Override
	public Product update(Product object) {
		return this.productRepo.save(object);
	}

	@PreAuthorize("@restAuthService.hasAccess('product.delete')")
	@Override
	public void delete(Integer id) {
		this.productRepo.delete(id);
	}

	@PreAuthorize("@restAuthService.hasAccess('product.getOne')")
	@Override
	public Product getOne(Integer id) {
		return this.productRepo.findOne(id);
	}

	@PreAuthorize("@restAuthService.hasAccess('product.getAll')")
	@Override
	public List<Product> getAll() {
		return this.productRepo.findAll();
	}

	@PreAuthorize("@restAuthService.hasAccess('product.listPageable')")
	@Override
	public Page<Product> listPageable(Pageable pageable) {
		return this.productRepo.findAll(pageable);
	}

}
