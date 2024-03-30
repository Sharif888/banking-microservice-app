package com.cts.transactions.model;

import java.time.LocalDateTime;
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
public class TransactionHistory {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	
	@Column(name = "Type")
	private String transactionType;
	
	@Column(name = "CustomerId")
	private Integer customerId;
	
	@Column(name = "SourceAcc")
	private Integer sourceAccountId;
	
	@Column(name = "DestAcc")
	private Integer destinationAccountId;
	
	@Column(name = "Amount")
	private double amount;
	
	@Column(name = "Charge")
	private double serviceCharge;
	
	@Column(name = "DateTime")
	private LocalDateTime dateOfTransaction;
	
	@Column(name = "status")
	private String transactionStatus;
	
	@Column(name = "Balance")
	private double balance;

	public TransactionHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionHistory(String transactionType, Integer customerId, Integer sourceAccountId,
			Integer destinationAccountId, double amount, LocalDateTime dateOfTransaction, String transactionStatus,
			double balance,double serviceCharge) {
		super();
		this.transactionType = transactionType;
		this.customerId = customerId;
		this.sourceAccountId = sourceAccountId;
		this.destinationAccountId = destinationAccountId;
		this.amount = amount;
		this.dateOfTransaction = dateOfTransaction;
		this.transactionStatus = transactionStatus;
		this.balance = balance;
		this.serviceCharge = serviceCharge;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Integer sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public Integer getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(Integer destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public LocalDateTime getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
