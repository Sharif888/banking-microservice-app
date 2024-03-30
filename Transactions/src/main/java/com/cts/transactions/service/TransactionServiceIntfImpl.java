package com.cts.transactions.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.transactions.client.AccountClient;
import com.cts.transactions.client.RuleClient;
import com.cts.transactions.exceptions.AccountNotFoundException;
import com.cts.transactions.exceptions.InsufficientBalanceException;
import com.cts.transactions.exceptions.NoTransactionHistoryException;
import com.cts.transactions.model.RuleStatus;
import com.cts.transactions.model.TransactionHistory;
import com.cts.transactions.model.TransactionStatus;
import com.cts.transactions.repository.TransactionHistoryRepository;

//Implementation of the Transaction Service Interface
//Marked as Service annotation for the automatic definition of beans
@Service
public class TransactionServiceIntfImpl implements TransactionServiceIntf{

	//Configuring Logger
	Logger logger = LoggerFactory.getLogger(TransactionServiceIntfImpl.class);
	
	//Autowired - Account Microservice's Client
	@Autowired 
	AccountClient accClient;
	
	//Autowired - Rule Microservice's Client
	@Autowired 
	RuleClient ruleClient;
	
	//Autowired - Transaction History Repository variable
	@Autowired
	TransactionHistoryRepository transacRepo;
	
	
	//Overridden deposit method of Transaction Interface
	/*
	 * @@@@Arguments@@@@
	 * @Auth Token 
	 * @Account ID
	 * @Amount to be deposited
	 * @Returns Transaction status
	 */
	@Override
	public TransactionStatus deposit(String requestTokenHeader,Integer accId, Double amount) {

		logger.info("Calling Account for deposit in Transaction Microservice");
		
		//Calling deposit method using account client and saving the transaction
		try {
			TransactionStatus status = accClient.deposit(requestTokenHeader, accId, amount);
			transacRepo.save(new TransactionHistory("Deposit", accClient.getCustomerId(accId, requestTokenHeader),accId,accId,amount,LocalDateTime.now(),"Success",accClient.getBalance(accId, requestTokenHeader),0));
			
			//Returning transaction status
			return status;
		} catch (Exception e) {
			
			//Throwing Account not found exception
			throw new AccountNotFoundException();
		}
	}
	
	//Overridden withdraw method of Transaction Interface
	/*
	 * @@@@Arguments@@@@
	 * @Auth Token 
	 * @Account ID
	 * @Amount to be withdrawn
	 * @Returns Transaction status
	 */
	@Override
	public TransactionStatus withdraw(String requestTokenHeader, Integer accId, Double amount) {

		logger.info("Calling Account for withdraw in Transaction Microservice");
		
		RuleStatus status = null;
		
		/*
		 * Checking minimum balance criteria using rule client
		 * 
		 * #If condition is not met, returned Transaction status as "Failed"
		 */
		try {
			status = ruleClient.calculateMinimumBalance(accClient.getBalance(accId, requestTokenHeader), amount);
		} catch (Exception e) {
			return new TransactionStatus(accId,accClient.getBalance(accId, requestTokenHeader),"Failed");
		}
		
		/* 
		 * If minimum balance method returned "allowed"
		 * Proceeding with withdraw method using account client and saving the transaction
		 */
		if(status.getStatus().compareTo("allowed")==0)
		{
			TransactionStatus transcStatus = accClient.withdraw(requestTokenHeader, accId, amount);
			transacRepo.save(new TransactionHistory("Withdraw", accClient.getCustomerId(accId, requestTokenHeader),accId,accId,amount,LocalDateTime.now(),"Success",accClient.getBalance(accId, requestTokenHeader),0.025*amount));
			
			//Returning transaction status
			return transcStatus;
		}
		else
		{
			//Thrown Insufficient Balance Exception if returned Failure from withdraw method
			throw new InsufficientBalanceException();	
		}
		
	}
	
	//Overridden transfer method of Transaction Interface
	/*
	 * @@@@Arguments@@@@
	 * @Auth Token 
	 * @Source Account ID
	 * @Destination Account ID
	 * @Amount to be transferred
	 * @Returns Transaction status
	 */
	@Override
	public TransactionStatus transfer(String requestTokenHeader, Integer accIdSource, Integer accIdDest,
			Double amount) {

		logger.info("Calling Account for transfer in Transaction Microservice");
		
		RuleStatus status = null;
		
		//Calling calculate minimum balance method of rule client or thrown account not found if declined 
		try {
		 status = ruleClient.calculateMinimumBalance(accClient.getBalance(accIdSource, requestTokenHeader), amount);
		} catch (Exception e) {
			throw new AccountNotFoundException();
		}
		
		//If transaction is allowed proceed with the money transfer using account client
		//Or throw Account Not Found Exception if some failure occurs
		if(status.getStatus().compareTo("allowed")==0)
		{
			try {
				if(accClient.getAccount(accIdDest, requestTokenHeader).getAccId()==accIdDest)
				{
					TransactionStatus transcStatus = accClient.withdraw(requestTokenHeader, accIdSource, amount);
					accClient.deposit(requestTokenHeader, accIdDest, amount);
					transacRepo.save(new TransactionHistory("Transfer", accClient.getCustomerId(accIdSource, requestTokenHeader),accIdSource,accIdDest,amount,LocalDateTime.now(),"Success",accClient.getBalance(accIdSource, requestTokenHeader),0.025*amount));
					transacRepo.save(new TransactionHistory("Transfer", accClient.getCustomerId(accIdDest, requestTokenHeader),accIdSource,accIdDest,amount,LocalDateTime.now(),"Success",accClient.getBalance(accIdDest, requestTokenHeader),0));
					return transcStatus;
				}
				else {
					throw new AccountNotFoundException();
				}
			} catch (Exception e) {
				throw new AccountNotFoundException();
			}
		}
		else
		{
			throw new InsufficientBalanceException();	
		}
	
	}
	
	//Overridden get transactions method of Transaction Interface
	/*
	 * @@@@Arguments@@@@
	 * @Auth Token 
	 * @Customer Id
	 * @Returns List of Transactions 
	 */
	@Override
	public List<TransactionHistory> transaction(String requestTokenHeader, Integer custId) {

		logger.info("Getting list in service in Transaction Microservice");
		
		/*
		 * Fetching all the transactions and filtering using customer id
		 * 
		 * #If records found, return Transactions List or Throw No Transaction Exception
		 */
		List<TransactionHistory> arr = transacRepo.findAll().stream().filter(transac->transac.getCustomerId().equals(custId)).collect(Collectors.toList());
		if(arr.size()==0)
		{
			throw new NoTransactionHistoryException();
		}
		else 
		{
			return arr;
		}
	}

}
