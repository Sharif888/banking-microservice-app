package com.cts.accountmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.accountmanagement.clients.AuthorizingClient;
import com.cts.accountmanagement.exception.UnauthorizedAccessException;
import com.cts.accountmanagement.model.Account;
import com.cts.accountmanagement.model.AccountCreationStatus;
import com.cts.accountmanagement.model.Statement;
import com.cts.accountmanagement.model.TransactionStatus;
import com.cts.accountmanagement.service.AccountServiceIntf;

//Account Controller class 
@RestController
@CrossOrigin(origins = "*")
public class AccountController {
	
	//Configuring logger
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	//Autowired - Authorizing Microservice's Client
	@Autowired
	private AuthorizingClient authorizingClient;
	
	//Autowired - Account Service Interface object
	@Autowired
	private AccountServiceIntf accServiceIntf;
	
	//Post mapping with create account route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Customer Id - Path Variable 
	 * @Returns List of account creation status
	 */
	@PostMapping(value = "/createaccount/{custId}")
	public List<AccountCreationStatus> createCustomerAccs(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@PathVariable("custId")Integer custId) {
		
		logger.info("Inside Create Customer Accounts");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		if (authResponse==true) {
			
			//Creating new Accounts
			List<AccountCreationStatus> status = accServiceIntf.createCustomerAccs(custId);
			logger.info("Exiting Create Customer Accounts");
			
			//Returning List of status
			return status;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with get customer's accounts route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Customer Id - Path Variable 
	 * @Returns List of accounts
	 */
	@GetMapping("/getcustomeraccounts/{custId}")
	public List<Account> getCustomerAccounts(@PathVariable("custId") int custId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
	{
		logger.info("Inside Getting Customer Accounts");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Getting customer's accounts
			List<Account> accs = accServiceIntf.getCustomerAccounts(custId);
			logger.info("Exiting Getting Customer Accounts");
			
			//Returning list of accounts
			return accs;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with get particular account route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id - Path Variable 
	 * @Returns Account
	 */
	@GetMapping("/getaccount/{accId}")
	public Account getAccount(@PathVariable("accId") Integer accId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
	{
		logger.info("Inside Getting Account");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Getting account 
			Account account = accServiceIntf.getAccount(accId);
			
			logger.info("Exiting Getting Account");
			
			//Returning account
			return account;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with get account balance route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id - Path Variable 
	 * @Returns Account Balance
	 */
	@GetMapping("/getbalance/{accId}")
	public Double getBalance(@PathVariable("accId") Integer accId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
	{
		logger.info("Inside Getting Balance");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Getting account balance
			Double balance = accServiceIntf.getBalance(accId);
			
			logger.info("Exiting getBalance");
			
			//Returning account balance
			return balance;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with get customer id route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id - Path Variable 
	 * @Returns Customer Id
	 */
	@GetMapping("/getcustomerid/{accId}")
	public Integer getCustomerId(@PathVariable("accId") Integer accId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
	{
		logger.info("Inside Getting Customer Id");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Getting Customer id
			Integer id = accServiceIntf.getCustomerId(accId);
			
			logger.info("Exiting Getting Customer Id");
			
			//Returning Customer id
			return id;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with get account's statements route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id
	 * @From Date
	 * @To Date
	 * @Returns List of Statements
	 */
	@GetMapping("/getaccountstatement")
	public List<Statement> getAccountStatement(Integer accId,String fromDate,String toDate,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
	{
		logger.info("Inside Getting Customer AccountStatement");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		//Checking for response and getting statements using date filters
		if (authResponse==true) {
			LocalDate fDate = LocalDate.parse(fromDate);
			LocalDate tDate = LocalDate.parse(toDate).plusDays(1);
			List<Statement> statements = accServiceIntf.getAccountStatement(accId,fDate,tDate);
			
			logger.info("Exiting Getting Customer AccountStatement");
			
			//Returning List of Statements
			return statements;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Post mapping with deposit route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id - Path Variable 
	 * @Amount to be deposited - Path Variable
	 * @Returns Transaction status
	 */
	@PostMapping(value = "/deposit/{accId}/{amt}")
	public TransactionStatus deposit(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@PathVariable("accId")Integer accId,@PathVariable("amt")Double amount) {
		logger.info("Inside Deposit");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Calling deposit method
			TransactionStatus status = accServiceIntf.deposit(accId,amount);
			logger.info("Exiting Deposit");
			
			//Returning Status
			return status;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Post mapping with withdraw route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id - Path Variable 
	 * @Amount to be withdrawn - Path Variable
	 * @Returns Transaction status
	 */
	@PostMapping(value = "/withdraw/{accId}/{amt}")
	public TransactionStatus withdraw(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@PathVariable("accId")Integer accId,@PathVariable("amt")Double amount) {
		logger.info("Inside Withdraw");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Calling withdraw method
			TransactionStatus status = accServiceIntf.withdraw(accId,amount);
			
			logger.info("Exiting Withdraw");
			
			//Returning Transaction Status
			return status;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
}
