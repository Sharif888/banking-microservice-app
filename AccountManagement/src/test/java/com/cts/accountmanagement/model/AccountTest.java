package com.cts.accountmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
	
	Account account= new Account();
	
	@Test
	public void testAccId()
	{
		account.setAccId(1);
		assertEquals(account.getAccId(),1);
	}
	
	@Test
	public void testCustId()
	{
		account.setCustId(1);
		assertEquals(account.getCustId(),1);
	}
	
	@Test
	public void testBalance()
	{
		account.setBalance(10000.0);
		assertEquals(account.getBalance(),10000.0);
	}
	
	@Test
	public void testAccountType()
	{
		account.setAccType("Savings");
		assertEquals(account.getAccType(),"Savings");
	}
	

}
