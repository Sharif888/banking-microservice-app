package com.cts.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountCreationStatusTest {

	@Autowired
	private AccountCreationStatus accountCreationStatus;
	
	
	@Test
	public void getSetter()
	{
		accountCreationStatus=new AccountCreationStatus();
		accountCreationStatus.setAccId(1);
		accountCreationStatus.setAccType("savings");
		accountCreationStatus.setMessage("success");
		
		assertEquals(1,accountCreationStatus.getAccId());
		assertEquals("savings",accountCreationStatus.getAccType());
		assertEquals("success",accountCreationStatus.getMessage());
	}
	
	
	@Test
	public void getGetter()
	{
		accountCreationStatus=new AccountCreationStatus();
		accountCreationStatus.setAccId(1);
		accountCreationStatus.setAccType("savings");
		accountCreationStatus.setMessage("success");
		
		assertEquals(accountCreationStatus.getAccId(), 1);
		assertEquals(accountCreationStatus.getAccType(), "savings");
		assertEquals(accountCreationStatus.getMessage(), "success");
	}
}
