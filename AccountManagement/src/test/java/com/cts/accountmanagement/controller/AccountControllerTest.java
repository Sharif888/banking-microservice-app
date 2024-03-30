package com.cts.accountmanagement.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cts.accountmanagement.clients.AuthorizingClient;
import com.cts.accountmanagement.controller.AccountController;
import com.cts.accountmanagement.model.Account;
import com.cts.accountmanagement.model.AccountCreationStatus;
import com.cts.accountmanagement.model.Statement;
import com.cts.accountmanagement.model.TransactionStatus;
import com.cts.accountmanagement.service.AccountServiceIntf;


@ExtendWith(SpringExtension.class)
public class AccountControllerTest {
	
	@InjectMocks
	private AccountController accController;
	
	@Mock
	private AuthorizingClient authorizingClient;
	
	@Mock
	private AccountServiceIntf accServiceIntf;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	String token = "Bearer token";
	
	@Test
	public void testCreateCustomerAcc()
	{
		List<AccountCreationStatus> status = new ArrayList<>();
		AccountCreationStatus stat1= new AccountCreationStatus();
		AccountCreationStatus stat2= new AccountCreationStatus();
		stat1.setAccId(1);
		stat1.setAccType("Savings");
		stat1.setMessage("Transaction Successful");
		status.add(stat1);
		
		stat2.setAccId(2);
		stat2.setAccType("Current");
		stat2.setMessage("Transaction Successful");
		status.add(stat2);
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.createCustomerAccs(1)).thenReturn(status);
		assertEquals(status,accController.createCustomerAccs(token, 1));
	}
	
	@Test
	public void testGetCustomerAccount()
	{
		Account acc1= new Account();
		Account acc2= new Account();
		List<Account> accs= new ArrayList<>();
		acc1.setAccId(1);
		acc1.setAccType("Savings");
		acc1.setBalance(10000.0);
		acc1.setCustId(1);
		accs.add(acc1);
		
		acc2.setAccId(2);
		acc2.setAccType("Current");
		acc2.setBalance(10000.0);
		acc2.setCustId(1);
		accs.add(acc2);
		
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.getCustomerAccounts(1)).thenReturn(accs);
		assertEquals(accs,accController.getCustomerAccounts(1, token));
	}
	
	
	@Test
	public void testGetAccount()
	{
		Account acc= new Account();
		acc.setAccId(1);
		acc.setAccType("Savings");
		acc.setBalance(10000.0);
		acc.setCustId(1);
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.getAccount(1)).thenReturn(acc);
		assertEquals(acc,accController.getAccount(1, token));
	}
	
	
	@Test
	public void testGetBalance()
	{
		Double balance= 10000.0;
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.getBalance(1)).thenReturn(balance);
		assertEquals(balance,accController.getBalance(1, token));
	}
	
	
	
	
	@Test
	public void testGetCustomerId()
	{
		Integer id=1;
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.getCustomerId(1)).thenReturn(id);
		assertEquals(id,accController.getCustomerId(1, token));
	}

	
	@Test
	public void testGetAccountStatement()
	{
		//DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Statement stat= new Statement();
		stat.setAccId(1);
		stat.setAccType("Savings");
		stat.setCheqRefNo("qqqqq");
		stat.setClosingBal(20000.0);
		stat.setSmtId(1);
		stat.setTransacDate(null);
		stat.setTransacType("Deposit");
		List<Statement> statements= new ArrayList<>();
		statements.add(stat);
		LocalDate fDate = LocalDate.parse("2021-08-09");
	    LocalDate tDate = LocalDate.parse("2021-08-09").plusDays(1);
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.getAccountStatement(1,fDate,tDate)).thenReturn(statements);
		assertEquals(statements,accController.getAccountStatement(1, "2021-08-09", "2021-08-09", token));
	}
	
	
	
	@Test
	public void testDeposit()
	{
		TransactionStatus status= new TransactionStatus();
		status.setAccId(1);
		status.setBalance(20000.0);
		status.setMessage("Transaction Successfull");
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.deposit(1,10000.0)).thenReturn(status);
		assertEquals(status,accController.deposit(token, 1, 10000.0));
	}
	
	
	@Test
	public void testWithdraw()
	{
		TransactionStatus status= new TransactionStatus();
		status.setAccId(1);
		status.setBalance(19000.0);
		status.setMessage("Transaction Successfull");
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(accServiceIntf.withdraw(1,1000.0)).thenReturn(status);
		assertEquals(status,accController.withdraw(token, 1, 1000.0));
	}
	

}
