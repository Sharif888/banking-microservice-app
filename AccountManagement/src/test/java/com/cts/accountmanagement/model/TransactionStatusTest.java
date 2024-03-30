package com.cts.accountmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TransactionStatusTest {
	
	TransactionStatus tran= new TransactionStatus();
	
	@Test
	public void testAccId()
	{
		tran.setAccId(101);
		assertEquals(tran.getAccId(),101);
	}
	
	@Test
	public void testBalance()
	{
		tran.setBalance(10000.0);
		assertEquals(tran.getBalance(),10000.0);
	}
	
	public void testMessage()
	{
		tran.setMessage("Success");
		assertEquals(tran.getMessage(),"Success");
	}

}
