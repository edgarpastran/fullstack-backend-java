package com.edpas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Product information")
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;
	
	@ApiModelProperty(notes = "Name must have at least 3 characters")
	@Size(min = 3, message = "Name must have at least 3 characters")
	@Column(name = "name", nullable = false, length = 100)
	private String name;	
	
	@ApiModelProperty(notes = "Brand must have at least 3 characters")
	@Size(min = 3, message = "Brand must have at least 3 characters")
	@Column(name = "brand", nullable = false, length = 50)
	private String brand;
	
	@ApiModelProperty(notes = "Price must be greater or equal than zero")
	@Min(value = 0)
	@Column(name = "price", nullable = false)
	private Double price;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
		
}
