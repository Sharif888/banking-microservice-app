package com.cts.accountmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class AccountCreationStatus {
	private Integer accId;
	private String message;
	private String accType;
	
	public AccountCreationStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountCreationStatus(Integer accId, String message, String accType) {
		super();
		this.accId = accId;
		this.message = message;
		this.accType = accType;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	
	
}
