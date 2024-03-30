package com.cts.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cts.customer.clients.AccountClient;
import com.cts.customer.clients.AuthorizingClient;
import com.cts.customer.exception.UserNotFoundException;
import com.cts.customer.model.AccountCreationStatus;
import com.cts.customer.model.Customer;
import com.cts.customer.model.CustomerCreationStatus;
import com.cts.customer.model.User;
import com.cts.customer.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

	
	@InjectMocks
	CustServiceIntfImpl custservice;
	
	@Mock
	AccountClient accclient;
	
	@Mock
	CustomerRepository custRepo;
	
	@Mock
	AuthorizingClient authclient;
	
	
	@Test
	public void addCustomerFailed()
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
		cc.setMessage("User Already Exists");
		
		Optional<Customer> cust= Optional.of(customer);
		when(custRepo.findById(customer.getCustId())).thenReturn(Optional.of(customer));
//		when(cust.isPresent()).thenReturn(true);
		assertNotEquals(cc.getMessage(), custservice.addCustomer(customer, "token").getMessage());
	}
	
	@Test
	public void addCustomerSuccess()
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath123");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("User Created Successfully");
		
		User user=new User("sharath123","sharath123",1,"customer");
		
		Optional<Customer> cust= Optional.of(customer);
		when(custRepo.findById(customer.getCustId())).thenReturn(Optional.empty());
		List<Customer> li=new ArrayList<>();
//		li.add(customer);
		when(custRepo.findAll()).thenReturn(li);
		int flag=2;
//		when(flag).thenReturn(flag);
//		when(customer.getCustUname().compareTo(customer.getCustUname())).thenReturn(2);
		AccountCreationStatus acc=new AccountCreationStatus();
		acc.setAccId(1);
		acc.setAccType("savings");
		acc.setMessage("success");
		List<AccountCreationStatus> lia=new ArrayList<AccountCreationStatus>();
		lia.add(acc);
		Mockito.when(accclient.createCustomerAccs("token", 1)).thenReturn(lia);
//		when(accc.)
		assertEquals(cc.getMessage(), custservice.addCustomer(customer, "token").getMessage());
		
	}
	
	
	
	@Test
	public void addCustomerNameExistsFailed()
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath123");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		
		CustomerCreationStatus cc=new CustomerCreationStatus();
		cc.setCustId(1);
		cc.setMessage("User Name Already Taken");
		
		User user=new User("sharath123","sharath123",1,"customer");
		
		Optional<Customer> cust= Optional.of(customer);
		when(custRepo.findById(customer.getCustId())).thenReturn(Optional.empty());
		List<Customer> li=new ArrayList<>();
		li.add(customer);
		when(custRepo.findAll()).thenReturn(li);
		assertEquals(cc.getMessage(), custservice.addCustomer(customer, "token").getMessage());
		
	}
	
//	@Test
//	public void addCustomerNameExistsFlagFailed()
//	{
//		Customer customer = new Customer();
//		customer.setCustId((Integer)1);;
//		customer.setCustName("sharath");
//		customer.setCustPass("sharath123");
//		customer.setCustAddress("kolkata");
//		customer.setCustDob(null);
//		customer.setCustPanNo("9247666");
//		customer.setCustUname("sharath");
//		
//		CustomerCreationStatus cc=new CustomerCreationStatus();
//		cc.setCustId(1);
//		cc.setMessage("User Name Already Taken");
//		
//		User user=new User("sharath123","sharath123",1,"customer");
//		
////		Optional<Customer> cust= Optional.of(customer);
//		when(custRepo.findById(customer.getCustId())).thenReturn(Optional.empty());
////		when(cust.getCustUname().compareTo(customer.getCustUname()))
//		List<Customer> li=new ArrayList<>();
//		li.add(customer);
//		when(custRepo.findAll()).thenReturn(li);
//		Customer cust=new Customer();
//		cust.setCustUname("sharath");
////		when(li.get(0).getCustUname()).thenReturn("sharath");
//		when(customer.getCustUname()).thenReturn("sharath");
////		when(li.get(0).getCustUname().compareTo(customer.getCustUname())).thenReturn(0);
//		assertEquals(cc.getMessage(), custservice.addCustomer(customer, "token").getMessage());
//		
//	}
	
	
	@Test
	 public void getCustomerDetails()
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");
		
		
		when(custRepo.findById(1)).thenReturn(Optional.of(customer));
		assertEquals(customer.getCustId(), custservice.getCustomerDetails(1).getCustId());
	}
	
	@Test
	 public void getCustomerDetailsFailed() throws RuntimeException,UserNotFoundException
	{
		Customer customer = new Customer();
		customer.setCustId((Integer)1);;
		customer.setCustName("sharath");
		customer.setCustPass("sharath");
		customer.setCustAddress("kolkata");
		customer.setCustDob(null);
		customer.setCustPanNo("9247666");
		customer.setCustUname("sharath");

		
		when(custRepo.findById(1)).thenReturn(Optional.empty());
assertThrows(UserNotFoundException.class, ()->custservice.getCustomerDetails(1));
	}
	
	
	
}
