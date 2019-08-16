package com.edpas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Purchase detail information")
@Entity
@Table(name = "purchase_detail")
public class PurchaseDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPurchaseDetail;
	
	@ApiModelProperty(notes = "Purchase must not be null")
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_purchase", nullable = false, foreignKey = @ForeignKey(name = "fk_purchase_detail_ref_purchase"))
	private Purchase purchase;
	
	@ApiModelProperty(notes = "Product must not be null")
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "fk_purchase_detail_ref_product"))
	private Product product;
	
	@ApiModelProperty(notes = "Quantity must be greater or equal than one")
	@Min(value = 1)
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@ApiModelProperty(notes = "Amount must be greater or equal than zero")
	@Min(value = 0)
	@Column(name = "amount", nullable = false)
	private Double amount;

	public Integer getIdPurchaseDetail() {
		return idPurchaseDetail;
	}

	public void setIdPurchaseDetail(Integer idPurchaseDetail) {
		this.idPurchaseDetail = idPurchaseDetail;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
