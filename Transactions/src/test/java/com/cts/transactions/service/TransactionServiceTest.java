package com.cts.transactions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.cts.transactions.client.AccountClient;
import com.cts.transactions.client.RuleClient;
import com.cts.transactions.exceptions.AccountNotFoundException;
import com.cts.transactions.exceptions.NoTransactionHistoryException;
import com.cts.transactions.model.Account;
import com.cts.transactions.model.RuleStatus;
import com.cts.transactions.model.TransactionHistory;
import com.cts.transactions.model.TransactionStatus;
import com.cts.transactions.repository.TransactionHistoryRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.NoTransactionException;

@SpringBootTest
public class TransactionServiceTest {

	
	
	@InjectMocks
	TransactionServiceIntfImpl transactionImpl;
	
	@Mock
	private AccountClient accountFeign;

	@Mock
	private TransactionHistoryRepository transactionRepository;

	@Mock
	private RuleClient ruleFeign;


	@org.junit.jupiter.api.Test
	void depositTest()
	{
		String token = "Bearer token";
		TransactionStatus transactionStatus = new TransactionStatus(1, 9000.0, "SUCESS");
		when(accountFeign.deposit(token, 1, 1000.0)).thenReturn(transactionStatus);
		assertEquals(transactionStatus, transactionImpl.deposit(token, 1, 1000.0));
	}

	
	@Test
	void withdrawTest()
	{
		String token = "Bearer token";
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setAccId(1);
		transactionStatus.setBalance(20000.0);
		transactionStatus.setMessage("SUCESS");
		RuleStatus ruleStatus = new RuleStatus();
		ruleStatus.setStatus("allowed");
		double balance = 20000.0;
		when(accountFeign.getBalance(1, token)).thenReturn(balance);
		when(ruleFeign.calculateMinimumBalance(balance,2000.0)).thenReturn(ruleStatus);
		when(accountFeign.withdraw(token, 1, 2000.0)).thenReturn(transactionStatus);
		assertEquals(transactionStatus, transactionImpl.withdraw(token, 1, 2000.0));
	}
	@Test
	void transactionTest()
	{
		String token = "Bearer token";
		 List<TransactionHistory> list = new ArrayList<>();
		 TransactionHistory transactionHistory = new TransactionHistory();
		 transactionHistory.setCustomerId(1);
		 list.add(transactionHistory);
		 when(transactionRepository.findAll()).thenReturn(list);
		 assertEquals(list, transactionImpl.transaction(token, 1));
	}
	
	@Test
	void transactionTestAssertException()
	{
		String token = "Bearer token";
		 List<TransactionHistory> list = new ArrayList<>();
	
		 
		 when(transactionRepository.findAll()).thenReturn(list);
		 assertThrows(NoTransactionHistoryException.class, ()->transactionImpl.transaction(token, 1));
	}
	
	
	@Test
	void transferTest()
	{
		String token = "Bearer token";
		TransactionStatus transactionStatus = new TransactionStatus(1, 10000.0, "SUCESS");
		RuleStatus status = new RuleStatus("allowed");
		double balance = 10000.0;
		when(accountFeign.getBalance(1, token)).thenReturn(balance);
		when(ruleFeign.calculateMinimumBalance(balance,2000.0)).thenReturn(status);
		Account account = new Account(1,10000.0,"SAVING");
		account.setAccId(2);
		
		when(accountFeign.getAccount(2, token)).thenReturn(account);
		when(accountFeign.withdraw(token, 1, 2000.0)).thenReturn(transactionStatus);
		when(accountFeign.deposit(token, 2, 2000.0)).thenReturn(transactionStatus);
		
		assertEquals(transactionStatus, transactionImpl.transfer(token, 1, 2, 2000.0));
	}
	@Test
	void transferTestInvalidAccount()
	{
		String token = "Bearer token";
		RuleStatus status = new RuleStatus("allowed");
		double balance = 10000.0;
		when(accountFeign.getBalance(1, token)).thenReturn(balance);
		when(ruleFeign.calculateMinimumBalance(balance,2000.0)).thenReturn(status);
		Account account = new Account(1,10000.0,"SAVING");
		account.setAccId(2);
		
		when(accountFeign.getAccount(2, token)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, ()->transactionImpl.transfer(token, 1, 2, 2000.0));
	}
	@Test
	void transferTestInvalidAccount2()
	{
		String token = "Bearer token";
		RuleStatus status = new RuleStatus("allowed");
		double balance = 10000.0;
		when(accountFeign.getBalance(1, token)).thenReturn(balance);
		when(ruleFeign.calculateMinimumBalance(balance,2000.0)).thenReturn(status);
		Account account = new Account(1,10000.0,"SAVING");
		account.setAccId(2);
		
		when(accountFeign.getAccount(1, token)).thenReturn(account);
		
	
		assertThrows(AccountNotFoundException.class, ()->transactionImpl.transfer(token, 1, 2, 2000.0));
	}
	

}
