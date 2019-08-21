package com.edpas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Person information")
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPerson;
	
	@ApiModelProperty(notes = "First name must have at least 3 characters")
	@Size(min = 3, message = "First name must have at least 3 characters")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@ApiModelProperty(notes = "Last name must have at least 3 characters")
	@Size(min = 3, message = "Last name must have at least 3 characters")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@ApiModelProperty(notes = "Driver license must have 8 characters")
	@Size(min = 8, max = 8, message = "Driver license must have 8 characters")
	@Column(name = "driver_license", nullable = false, length = 8)
	private String driverLicense; 
	
	@ApiModelProperty(notes = "Telephone must have 10 characters")
	@Size(min = 10, max = 10, message = "Telephone must have 10 characters")
	@Column(name = "telephone", nullable = true, length = 10)
	private String telephone;
	
	@Email(message = "Email is not valid")
	@Column(name = "email", nullable = true, length = 50)
	private String email;
	
	
	public Integer getIdPerson() {
		return idPerson;
	}
	
	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
			
}
