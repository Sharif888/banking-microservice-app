package com.cts.transactions.model;

import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
public class Account {
	private Integer accId;
	private Integer custId;
	private Double balance;	
	private String accType;
	
	public Account(Integer custId, Double balance, String accType) {
		super();
		this.custId = custId;
		this.balance = balance;
		this.accType = accType;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	
	
}
