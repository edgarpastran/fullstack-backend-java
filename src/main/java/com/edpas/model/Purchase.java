package com.edpas.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Purchase information")
@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPurchase;
	
	@ApiModelProperty(notes = "Date must not be null")
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "date", nullable = false)
	private LocalDateTime date;	
	
	@ApiModelProperty(notes = "Person must not be null")
	@ManyToOne
	@JoinColumn(name = "id_person", nullable = false, foreignKey = @ForeignKey(name = "fk_purchase_ref_person"))
	private Person person;
	
	@ApiModelProperty(notes = "Amount must be greater or equal than zero")
	@Min(value = 0)
	@Column(name = "amount", nullable = false)
	private Double amount;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PurchaseDetail> purchaseDetails;
	
	public Integer getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Integer idPurchase) {
		this.idPurchase = idPurchase;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<PurchaseDetail> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}
	 
}
