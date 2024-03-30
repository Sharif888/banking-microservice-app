package com.cts.accountmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class TransactionStatus {
	private Integer accId;
	private Double balance;
	private String message;
	public TransactionStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionStatus(Integer accId, Double balance, String message) {
		super();
		this.accId = accId;
		this.balance = balance;
		this.message = message;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
