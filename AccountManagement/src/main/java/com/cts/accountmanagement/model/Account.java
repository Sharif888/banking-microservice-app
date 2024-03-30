package com.cts.accountmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer accId;
	@Column(name = "customerId")
	private Integer custId;
	@Column(name = "Balance")
	private Double balance;	
	@Column(name = "Type")
	private String accType;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Account(Integer custId, Double balance, String accType) {
		super();
		this.custId = custId;
		this.balance = balance;
		this.accType = accType;
	}



	public Account(Integer accId, Integer custId, Double balance, String accType) {
		super();
		this.accId = accId;
		this.custId = custId;
		this.balance = balance;
		this.accType = accType;
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
