package com.cts.accountmanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.accountmanagement.clients.RuleClient;
import com.cts.accountmanagement.exception.AccountNotFoundException;
import com.cts.accountmanagement.exception.UserNotFoundException;
import com.cts.accountmanagement.model.Account;
import com.cts.accountmanagement.model.AccountCreationStatus;
import com.cts.accountmanagement.model.Statement;
import com.cts.accountmanagement.model.TransactionStatus;
import com.cts.accountmanagement.repository.AccountRepository;
import com.cts.accountmanagement.repository.StatementRepository;

//Implementation of the Customer Service Interface
//Marked as Service annotation for the automatic definition of beans
@Service
public class AccountServiceIntfImpl implements AccountServiceIntf{

	//Configuring Logger
	Logger logger = LoggerFactory.getLogger(AccountServiceIntfImpl.class);
	
	//Autowired - Account Repository variable
	@Autowired
	private AccountRepository accRepo;
	
	//Autowired - Statement Repository variable
	@Autowired
	private StatementRepository smtRepo;
	
	//Autowired - Rule Microservice's Client
	@Autowired 
	RuleClient ruleClient;
	
	//Overridden create Customer accounts method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Customer ID
	 * @Returns List of created accounts
	 */
	@Override
	public List<AccountCreationStatus> createCustomerAccs(Integer custId) {
		
		logger.info("Creating customer accounts from account microservice");
		
		List<AccountCreationStatus> arr = new ArrayList<>();
		
		//Creating Savings Account
		Account account = new Account(custId,10000.00,"Savings");
		accRepo.save(account);
		arr.add(new AccountCreationStatus(account.getAccId(),"Account creation Successful","Savings"));
		
		//Creating Current Account
		account = new Account(custId,10000.00,"Current");
		accRepo.save(account);
		arr.add(new AccountCreationStatus(account.getAccId(),"Account creation Successful","Current"));
		
		logger.info("Created customer accounts from account microservice");
		
		//Returning List of Accounts Created
		return arr;
	}
	
	//Overridden get Customer accounts method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Customer ID
	 * @Returns List of customer's accounts
	 */
	@Override
	public List<Account> getCustomerAccounts(Integer custId) {

		logger.info("Getting Lists of accounts from account microservice");
		
		//Finding all accounts using provided customer id
		List<Account> accounts = accRepo.findAll().stream().filter(acc->acc.getCustId().equals(custId)).collect(Collectors.toList());
		
		/* Checking existence of accounts
		 * 
		 * @Return 
		 * 1. Throws User not Found exception - If accounts not found
		 * 2. Returns List of accounts - if size > 0
		 */
		if(accounts.size()==0)
		{
			throw new UserNotFoundException();
		}
		else
		{
			return accounts;
		}
		
	}
	
	//Overridden get Account method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Account ID
	 * @Returns customer's account
	 */
	@Override
	public Account getAccount(Integer accId) {

		logger.info("Getting Account from account microservice");
		
		//Finding Account using the account id provided
		Optional<Account> account = accRepo.findById(accId);
		
		/* Checking existence of accounts
		 * 
		 * @Return 
		 * 1. Returns List of accounts - if account found
		 * 2. Throws Account not Found exception - If accounts not found
		 */
		if(account.isPresent()) {
			return account.get();
		}
		else
		{
		throw new AccountNotFoundException();	
		}
	}
	
	//Overridden Deposit method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Account ID
	 * @Amount to be deposited
	 * @Returns Transaction Status
	 */
	@Override
	public TransactionStatus deposit(Integer accId, Double amount) {

		logger.info("Depositing in account in account microservice");
		
		//Finding Account using the account id provided
		Optional<Account> account = accRepo.findById(accId);
		
		/* Checking existence of accounts
		 * 
		 * @Return 
		 * 1. Depositing in account and returning status - if account found
		 * 2. Throws Account not Found exception - If accounts not found
		 */
		if(account.isPresent()) {
			account.get().setBalance(account.get().getBalance()+amount);
			accRepo.save(account.get());
			smtRepo.save(new Statement(accId, account.get().getAccType(), accId.toString()+"HK"+amount.toString(),LocalDate.now(), account.get().getBalance(),"Deposit"));
		    return new TransactionStatus(accId,account.get().getBalance(),"Transaction Successful");
		}
		else
		{
			logger.debug("Account not found");
			throw new AccountNotFoundException();	
		}
	}

	//Overridden Withdraw method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Account ID
	 * @Amount to be withdrawn
	 * @Returns Transaction Status
	 */
	@Override
	public TransactionStatus withdraw(Integer accId, Double amount) {

		logger.info("Withdrawing in account in account microservice");
		
		//Finding Account using the account id provided
		Optional<Account> account = accRepo.findById(accId);
		
		/* Checking existence of accounts
		 * Getting Service charge using rule client
		 * @Return 
		 * 1. Withdraw from account and returning status - if account found
		 * 2. Throws Account not Found exception - If accounts not found
		 */
		if(account.isPresent()) {
			double charge = ruleClient.getServiceCharge(amount);
			account.get().setBalance(account.get().getBalance()-amount-charge);
			accRepo.save(account.get());
			smtRepo.save(new Statement(accId, account.get().getAccType(), accId.toString()+"HK"+amount.toString(),LocalDate.now(), account.get().getBalance(),"Withdraw"));
		    return new TransactionStatus(accId,account.get().getBalance(),"Transaction Successful");
		}
		else
		{
			logger.debug("Account not found");
			throw new AccountNotFoundException();	
		}
	}
	
	//Overridden get balance method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Account ID
	 * @Returns Account Balance
	 */
	@Override
	public Double getBalance(Integer accId) {

		logger.info("Getting balance of account from account microservice");
		
		//Finding Account using account id provided
		Optional<Account> account = accRepo.findById(accId);
		
		/* Checking existence of accounts
		 * 
		 * @Return 
		 * 1. Account Balance - if account found
		 * 2. Throws Account not Found exception - If accounts not found
		 */
		if(account.isPresent()) {
			return account.get().getBalance();
		}
		else
		{
			logger.debug("Account not found");
			throw new AccountNotFoundException();	
		}
	}
	
	//Overridden get customer id method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Account ID
	 * @Returns Customer ID
	 */
	@Override
	public Integer getCustomerId(Integer accId) {

		logger.info("Getting CustId of account from account microservice");
		
		//Finding Account using account id provided
		Optional<Account> account = accRepo.findById(accId);
		
		/* Checking existence of accounts
		 * 
		 * @Return 
		 * 1. Customer Id - if account found
		 * 2. Throws Account not Found exception - If accounts not found
		 */		
		if(account.isPresent()) {
			return account.get().getCustId();
		}
		else
		{
			logger.debug("Account not found");
			throw new AccountNotFoundException();	
		}
	}
	
	//Overridden get account statement method of Account Interface
	/*
	 * @@@@Arguments@@@@
	 * @Account ID
	 * @From Date
	 * @To Date
	 * @Returns Customer ID
	 */
	@Override
	public List<Statement> getAccountStatement(Integer accId, LocalDate fDate, LocalDate tDate) {

		logger.info("Getting Account Statement of account from account microservice");
		
		//Finding Account using account id provided
		Optional<Account> account = accRepo.findById(accId);
		
		/* Checking existence of accounts
		 * Finding all statements for a particular account
		 * Filtering statements using from date and to date 
		 * @Return 
		 * 1. List of Statements - if account found
		 * 2. Throws Account not Found exception - If accounts not found
		 */	
		if(account.isPresent()) {
			List<Statement> statements = smtRepo.findAll().stream().filter(smt->smt.getAccId().equals(accId)).collect(Collectors.toList());
			return statements.stream().filter(smt->smt.getTransacDate().isAfter(fDate)&&smt.getTransacDate().isBefore(tDate)).collect(Collectors.toList());
		}
		else
		{
			logger.debug("Account not found");
			throw new AccountNotFoundException();	
		}
	}

}
