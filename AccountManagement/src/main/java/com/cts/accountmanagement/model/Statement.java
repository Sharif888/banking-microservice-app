package com.cts.accountmanagement.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Statement {
	public Statement(Integer accId, String accType, String cheqRefNo, LocalDate transacDate, double closingBal,
			String transacType) {
		super();
		this.accId = accId;
		this.accType = accType;
		this.cheqRefNo = cheqRefNo;
		this.transacDate = transacDate;
		this.closingBal = closingBal;
		this.transacType = transacType;
	}

	public Statement(Integer smtId, Integer accId, String accType, String cheqRefNo, LocalDate transacDate,
			double closingBal, String transacType) {
		super();
		this.smtId = smtId;
		this.accId = accId;
		this.accType = accType;
		this.cheqRefNo = cheqRefNo;
		this.transacDate = transacDate;
		this.closingBal = closingBal;
		this.transacType = transacType;
	}

	public Statement() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer smtId;
	
	@Column(name = "Account")
	private Integer accId;
	
	@Column(name = "AccType")
	private String accType;
	
	@Column(name = "cheqRefNo")
	private String cheqRefNo;
	
	@Column(name = "Date")
	private LocalDate transacDate;
	
	@Column(name = "Balance")
	private double closingBal;
	
	@Column(name = "type")
	private String transacType;

	public Integer getSmtId() {
		return smtId;
	}

	public void setSmtId(Integer smtId) {
		this.smtId = smtId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getCheqRefNo() {
		return cheqRefNo;
	}

	public void setCheqRefNo(String cheqRefNo) {
		this.cheqRefNo = cheqRefNo;
	}

	public LocalDate getTransacDate() {
		return transacDate;
	}

	public void setTransacDate(LocalDate transacDate) {
		this.transacDate = transacDate;
	}

	public double getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(double closingBal) {
		this.closingBal = closingBal;
	}

	public String getTransacType() {
		return transacType;
	}

	public void setTransacType(String transacType) {
		this.transacType = transacType;
	}
	
}
