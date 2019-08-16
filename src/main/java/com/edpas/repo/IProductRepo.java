package com.edpas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edpas.model.Product;

public interface IProductRepo extends JpaRepository<Product, Integer>{

}
