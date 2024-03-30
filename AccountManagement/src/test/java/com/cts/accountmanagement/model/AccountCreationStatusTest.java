package com.cts.accountmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountCreationStatusTest {
	
	
	AccountCreationStatus acc= new AccountCreationStatus();
	
	@Test
	public void testAccId()
	{
		acc.setAccId(121);
		assertEquals(acc.getAccId(),121);
	}
	
	@Test
	public void testMessage()
	{
		acc.setMessage("Success");
		assertEquals(acc.getMessage(),"Success");
	}
	
	@Test
	public void testAccType()
	{
		acc.setAccType("Current");
		assertEquals(acc.getAccType(),"Current");
	}

}
