package com.cts.accountmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class StatementTest {
	
	Statement stat= new Statement();
	
	@Test
	public void testSmtId()
	{
		stat.setSmtId(101);
		assertEquals(stat.getSmtId(),101);
	}
	
	@Test
	public void testAccId()
	{
		stat.setAccId(102);
		assertEquals(stat.getAccId(),102);
	}
	
	@Test
	public void testAccType()
	{
		stat.setAccType("Savings");
		assertEquals(stat.getAccType(),"Savings");
	}
	
	@Test
	public void testTransacDate()
	{
		stat.setTransacDate(LocalDate.now());
		assertEquals(stat.getTransacDate(),LocalDate.now());
	}
	
	@Test
	public void testBalance()
	{
		stat.setClosingBal(20000.0);
		assertEquals(stat.getClosingBal(),20000.0);
	}
	
	@Test
	public void testTransacType()
	{
		stat.setTransacType("Withdraw");
		assertEquals(stat.getTransacType(),"Withdraw");
	}

}
