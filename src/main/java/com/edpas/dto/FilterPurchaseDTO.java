package com.edpas.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class FilterPurchaseDTO {

	private String driverLicense;
	private String fullName;
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime date;
	
	public String getDriverLicense() {
		return driverLicense;
	}
	
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
		
}
