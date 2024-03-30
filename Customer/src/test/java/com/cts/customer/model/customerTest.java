package com.cts.customer.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.Before;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class customerTest {

	@Autowired
	private static Customer customer;
	
	@BeforeAll
	public static void setUp() throws Exception {
		customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
	}

	@Test
	public void testGetter() {
		customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		assertEquals((Integer)1, customer.getCustId());
		assertEquals("sharath", customer.getCustName());
		assertEquals("9247666", customer.getCustPanNo());
		assertEquals("sharath", customer.getCustPass());
		assertEquals("kolkata", customer.getCustAddress());
		assertEquals(null, customer.getCustDob());
	}

	@Test
	public void testSetter() {
		customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		assertEquals("sharath", customer.getCustName());
		assertEquals("sharath", customer.getCustPass());
		assertEquals("kolkata", customer.getCustAddress());
		assertEquals("9247666", customer.getCustPanNo());
		assertEquals((Integer)1, customer.getCustId());
	
	}
	
	@Test
	public void getCustId()
	{
		assertEquals((Integer)1, customer.getCustId());
	}
	
	@Test
	public void getCustName()
	{
		assertEquals("sharath", customer.getCustName());
	}
	
	@Test
	public void getCustAddress()
	{
		assertEquals("kolkata", customer.getCustAddress());
	}
	@Test
	public void getPanNo()
	{
		assertEquals("9247666", customer.getCustPanNo());
	}
	
	@Test
	public void getCustpassowrd()
	{
		assertEquals("sharath", customer.getCustPass());
	}
	
	
	
	
}
