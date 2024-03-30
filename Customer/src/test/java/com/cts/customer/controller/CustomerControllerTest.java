package com.cts.customer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.customer.clients.AuthorizingClient;
import com.cts.customer.exception.UnauthorizedAccessException;
import com.cts.customer.model.Customer;
import com.cts.customer.model.CustomerCreationStatus;
import com.cts.customer.service.CustServiceIntfImpl;


@SpringBootTest
public class CustomerControllerTest {

	
	@InjectMocks
	CustomerController custController;
	
	@Mock
	AuthorizingClient authclient;
	
	@Mock
	CustServiceIntfImpl custservice;
	
	String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFbXBsb3llZSIsImV4cCI6MTYxNjY5MzEwNCwiaWF0IjoxNjE2NjkxMzA0fQ._OoNumEfxD790xZXT33LvbGEbEhEmOWMoWO8ADLuzZg";
	
//	@Autowired
//	private MockMvc mvc;
	
	@Test
	public void createCustomerTest()
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("Id created successfully");
		
		when(authclient.authorizeTheRequest("Bearer "+token)).thenReturn(true);
		when(custservice.addCustomer(customer, "Bearer "+token)).thenReturn(cc);
		assertEquals(cc, custController.createCustomer("Bearer "+token, customer));
		
	}
	
	@Test
	public void createCustomerFailedTest() throws Exception
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("Id created successfully");
		
		when(authclient.authorizeTheRequest("Bearer "+token)).thenThrow(new UnauthorizedAccessException());
		when(custservice.addCustomer(customer, "Bearer "+token)).thenReturn(cc);
//		assertEquals(cc, custController.createCustomer("Bearer "+token, customer));
//		assertThrows(new UnauthorizedAccessException(), ()->custController.createCustomer("token", customer),"asf");
		
	}
	
	@Test
	public void createCustomerExceptionFailedTest() throws Exception
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("Id created successfully");
		
		when(authclient.authorizeTheRequest("Bearer "+token)).thenReturn(false);
//		assertThrows(new UnauthorizedAccessException(), ()->custController.createCustomer("token", customer));
//		assertEquals(new UnauthorizedAccessException(),custController.createCustomer("token", customer) );
		
		
	}
	
	@Test
	public void getCustomerSuccess()
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("Id created successfully");
		
		when(authclient.authorizeTheRequest("Bearer "+token)).thenReturn(true);
		when(custservice.getCustomerDetails(1)).thenReturn(customer);
		assertEquals(customer, custController.getCustomerDetails(1, "Bearer "+token));
	}
	
	@Test
	public void getCustomerFailed()
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("Id created successfully");
		
		when(authclient.authorizeTheRequest("Bearer "+token)).thenThrow(new UnauthorizedAccessException());
		when(custservice.getCustomerDetails(1)).thenReturn(customer);
//		assertEquals(customer, custController.getCustomerDetails(1, "Bearer "+token));
	}
	
	
}
