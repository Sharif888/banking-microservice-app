package com.cts.accountmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.cts.accountmanagement.model.Account;
import com.cts.accountmanagement.model.AccountCreationStatus;
import com.cts.accountmanagement.model.Statement;
import com.cts.accountmanagement.model.TransactionStatus;

//Methods of Account Microservice's service layer containing business logic 
public interface AccountServiceIntf {
	
	//1. To create customer Bank Accounts
	public List<AccountCreationStatus> createCustomerAccs(Integer custId);
	
	//2. To get customer accounts
	public List<Account> getCustomerAccounts(Integer custId);
	
	//3. To get single account
	public Account getAccount(Integer accId);
	
	//4. To deposit
	public TransactionStatus deposit(Integer accId,Double amount);
	
	//5. To withdraw
	public TransactionStatus withdraw(Integer accId, Double amount);
	
	//6. To get bank balance
	public Double getBalance(Integer accId);
	
	//7. To get customer id
	public Integer getCustomerId(Integer accId);
	
	//8. To get account statement
	public List<Statement> getAccountStatement(Integer accId,LocalDate fDate,LocalDate tDate);
	
}
