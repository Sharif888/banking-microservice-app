package com.cts.accountmanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.accountmanagement.model.Account;
import com.cts.accountmanagement.model.AccountCreationStatus;
import com.cts.accountmanagement.model.Statement;
import com.cts.accountmanagement.model.TransactionStatus;
import com.cts.accountmanagement.clients.RuleClient;
import com.cts.accountmanagement.exception.AccountNotFoundException;
import com.cts.accountmanagement.exception.UserNotFoundException;
import com.cts.accountmanagement.repository.AccountRepository;
import com.cts.accountmanagement.repository.StatementRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AccountServiceIntfImplTest {
	
	@InjectMocks
	private AccountServiceIntfImpl accService;
	
	@Mock
	private AccountRepository accRepo;
	
	@Mock
	private StatementRepository smtRepo;
	
	@Mock 
	RuleClient ruleClient;
	
	
	
	@Test
	public void testCreateCustomerAccs()
	{
		List<AccountCreationStatus> arr = new ArrayList<>();
		
		Account acc1= new Account();
		acc1.setAccId(1);
		acc1.setAccType("Savings");
		acc1.setBalance(10000.0);
		acc1.setCustId(1);
		AccountCreationStatus status1= new AccountCreationStatus();
		status1.setAccId(acc1.getAccId());
		status1.setAccType(acc1.getAccType());
		status1.setMessage("Account creation Successful");
		
		Account acc2= new Account();
		acc2.setAccId(2);
		acc2.setAccType("Current");
		acc2.setBalance(10000.0);
		acc2.setCustId(1);
		AccountCreationStatus status2= new AccountCreationStatus();
        status2.setAccId(acc2.getAccId());
        status2.setAccType(acc2.getAccType());
        status2.setMessage("Account creation Successful");
        Account account = new Account(1,10000.00,"Savings");
        arr.add(status1);
//        arr.add(status2);
        when(accRepo.save(account)).thenReturn(acc1);
        List<AccountCreationStatus> status3= accService.createCustomerAccs(1);
//        when(accRepo.save(acc2)).thenReturn(acc2);
		assertEquals(null,status3.get(0).getAccId());
	}
	
	
	
	@Test
	public void testGetCustomerAccount()
	{
	
		Account acc1= new Account();
		acc1.setAccId(1);
		acc1.setAccType("Savings");
		acc1.setBalance(10000.0);
		acc1.setCustId(1);
		
		Account acc2= new Account();
		acc2.setAccId(2);
		acc2.setAccType("Current");
		acc2.setBalance(10000.0);
		acc2.setCustId(1);
		
		List<Account> account= new ArrayList<>();
		account.add(acc1);
		account.add(acc2);
		when(accRepo.findAll()).thenReturn(account);
		assertEquals(account,accService.getCustomerAccounts(1));
	}
	

	
	
	@Test
	public void testGetAccount()
	{
		Account acc= new Account(1,10000.0, "Savings");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
	    assertEquals(acc,accService.getAccount(1));
	}
	
	
 
	@Test
	public void testDeposit()
	{
		Account acc= new Account(1, 10000.0, "Savings");
		Account acc1= new Account(1,11000.0,"Savings");
		Statement stat= new Statement(1, "Savings","qqqq",LocalDate.now(),acc1.getBalance(),"Deposit");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
		when(accRepo.save(acc1)).thenReturn(acc1);
		when(smtRepo.save(stat)).thenReturn(stat);
		TransactionStatus tran= new TransactionStatus(1, acc.getBalance(),"Transaction Successful");
		assertEquals(tran.getAccId(),accService.deposit(1, 1000.0).getAccId());
	}
	
	
	
	@Test
	public void testWithdraw()
	{
		Account acc= new Account(1, 20000.0, "Savings");
		Account acc1= new Account(1,18975.0,"Savings");
		Statement stat= new Statement(1, "Savings","qwerty",LocalDate.now(),acc1.getBalance(),"Withdraw");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
		when(ruleClient.getServiceCharge(1000.0)).thenReturn((0.025*1000));
		when(accRepo.save(acc)).thenReturn(acc1);
		when(smtRepo.save(stat)).thenReturn(stat);
		TransactionStatus tran= new TransactionStatus(1, acc1.getBalance(),"Transaction Successful");
		assertEquals(tran.getAccId(),accService.withdraw(1, 1000.0).getAccId());
	}

	
	

	@Test
	public void testGetBalance()
	{
		Account acc= new Account(1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
		assertEquals(acc.getBalance(),accService.getBalance(1));
	}

	@Test
	public void testGetCustomerId()
	{
		Account acc= new Account(1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
		assertEquals(acc.getCustId(),1);
	}

	
	
	@Test
	public void testGetAccountStatement()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
		Statement stat= new Statement(1,1,"Savings","nedcnu",LocalDate.now(),20000.0, "Deposit");
		Statement stat1 = new Statement(2,1,"Savings","ewnoinw",LocalDate.now(),25000.0, "Deposit");
		List<Statement> statements= new ArrayList<>();
//		statements.add(stat);
//		statements.add(stat1);
		when(smtRepo.findAll().stream().filter(smt->smt.getAccId().equals(1)).collect(Collectors.toList())).thenReturn(statements);
//		when(statements.stream().filter(smt->smt.getTransacDate().isAfter(LocalDate.now())&&smt.getTransacDate().isBefore(LocalDate.now())).collect(Collectors.toList())).thenReturn(statements);
		assertEquals(statements,accService.getAccountStatement(1, LocalDate.parse("2020-03-01"), LocalDate.parse("2021-03-01")));
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testGetAccountStatementFailed()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, ()->accService.getAccountStatement(1, LocalDate.now(), LocalDate.now()));
	
	}
	
	@Test
	public void getCustomerIdFailed()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, ()->accService.getCustomerId(1));
		
	}
	
	@Test
	public void getCustomerIdSUccess()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.of(acc));
		assertEquals(acc.getAccId(), accService.getCustomerId(1));
		
	}
	
	@Test
	public void getCustomerAccountsFailed()
	{
		Account accs= new Account(1,1,20000.0,"Savings");
		
		List<Account> li=new ArrayList<>();
		
		when(accRepo.findAll().stream().filter(acc->acc.getCustId().equals(1)).collect(Collectors.toList())).thenReturn(li);
		assertThrows(UserNotFoundException.class, ()->accService.getCustomerAccounts(1));
	}
   
	/////////////////////////////////////////////////////////
	
	@Test
	public void getAccountFailed()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, ()->accService.getAccount(1));
		
	}
	
	@Test
	public void depositFailed()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, ()->accService.deposit(1,(double)300));
		
	}
	
	@Test
	public void getWithdrawFailed()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, ()->accService.withdraw(1,(double)1000));
		
	}
	
	@Test
	public void getBalenceFailed()
	{
		Account acc= new Account(1,1,20000.0,"Savings");
		when(accRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, ()->accService.getBalance(1));
		
	}
}
