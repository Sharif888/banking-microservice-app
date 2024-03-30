package com.cts.customer.model;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerCreationStatusTest {

	
	@Autowired
	private CustomerCreationStatus customerCreationStatus;
	
//	@BeforeAll
//	public static void setUp() throws Exception {
//		
//		customerCreationStatus.setCustId((Integer)1);
//		customerCreationStatus.setMessage("sucess");
//	}
	
	@Test
	public void testGetter() {
		customerCreationStatus=new CustomerCreationStatus();
		customerCreationStatus.setCustId((Integer)1);
		customerCreationStatus.setMessage("sucess");
		assertEquals(customerCreationStatus.getCustId(), 1);
		assertEquals(customerCreationStatus.getMessage(), "sucess");
	}
	
	@Test
	public void testSetter() {
		customerCreationStatus=new CustomerCreationStatus();
		customerCreationStatus.setCustId((Integer)1);
		customerCreationStatus.setMessage("sucess");
		assertEquals(customerCreationStatus.getCustId(), 1);
		assertEquals(customerCreationStatus.getMessage(), "sucess");
	}
	
	
}
