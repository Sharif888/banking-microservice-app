package com.cts.transactions.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.transactions.client.AuthorizingClient;
import com.cts.transactions.exceptions.UnauthorizedAccessException;
import com.cts.transactions.model.TransactionHistory;
import com.cts.transactions.model.TransactionStatus;
import com.cts.transactions.service.TransactionServiceIntf;

//Transaction Controller class 
@RestController
@CrossOrigin
public class TransactionController {
	
	//Configuring logger
	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	//Autowired - Authorizing Microservice's Client
	@Autowired
	private AuthorizingClient authorizingClient;
	
	//Autowired - Transaaction Service Interface object
	@Autowired
	private TransactionServiceIntf transactionService;
	
	//Post mapping with deposit route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id
	 * @Amount to be deposited
	 * @Returns Transaction Status
	 */
	@PostMapping(value = "/deposit")
	public TransactionStatus deposit(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@RequestParam("accId") Integer accId,@RequestParam("amount") Double amount) {
		
		logger.info("Inside Deposit in Transaction Microservice");
		
		//Verifying user token		
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Calling deposit method from service
			TransactionStatus status = transactionService.deposit(requestTokenHeader,accId,amount);
			
			logger.info("Exiting Deposit in Transaction Microservice");
			
			//Returning Transaction Status
			return status;
		}
		else
		{
			//Error Thrown
			logger.info("Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Post mapping with withdraw route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Account Id
	 * @Amount to be withdrawn
	 * @Returns Transaction Status
	 */
	@PostMapping(value = "/withdraw")
	public TransactionStatus withdraw(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@RequestParam("accId")  Integer accId,@RequestParam("amount")  Double amount) {
		logger.info("Inside Withdraw in Transaction Microservice");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Calling withdraw method from service
			TransactionStatus status = transactionService.withdraw(requestTokenHeader,accId,amount);
			
			logger.info("Exiting Withdraw in Transaction Microservice");
			
			//Returning Transaction Status
			return status;
		}
		else
		{
			//Error Thrown
			logger.info("Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Post mapping with deposit route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Source Account Id
	 * @Destinaton Account Id
	 * @Amount to be transferred
	 * @Returns Transaction Status
	 */
	@PostMapping(value = "/transfer")
	public TransactionStatus transfer(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@RequestParam("accIdSource") Integer accIdSource,@RequestParam("accIdDest") Integer accIdDest,@RequestParam("amount") Double amount) {
		logger.info("Inside Transfer in Transaction Microservice");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			
			//Calling transfer method from service
			TransactionStatus status = transactionService.transfer(requestTokenHeader,accIdSource,accIdDest,amount);
			
			logger.info("Exiting Transfer in Transaction Microservice");
			
			//Returning Transaction Status
			return status;
		}
		else
		{
			//Error Thrown
			logger.info("Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with transaction list route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Customer Id
	 * @Returns List of Transaction History
	 */
	@GetMapping(value = "/transactionlist")
	public List<TransactionHistory> transaction(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@RequestParam("custId") Integer custId) {
		logger.info("Inside getting transaction list in Transaction Microservice");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			System.out.println(custId);
			
			//Calling transaction method from service
			List<TransactionHistory> list = transactionService.transaction(requestTokenHeader,custId);
			
			logger.info("Exiting getting transaction list in Transaction Microservice");
			
			//Returning List of Transactions
			return list;
		}
		else
		{
			//Error Thrown
			logger.info("Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
}
