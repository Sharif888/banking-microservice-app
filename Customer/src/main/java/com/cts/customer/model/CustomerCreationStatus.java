package com.cts.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Customer Creation Status Model with required attributes
//Using Getter-Setter notations of Lombok

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class CustomerCreationStatus {
	private Integer custId;
	private String message;
	
	public CustomerCreationStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomerCreationStatus(Integer custId, String message) {
		super();
		this.custId = custId;
		this.message = message;
	}

	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
