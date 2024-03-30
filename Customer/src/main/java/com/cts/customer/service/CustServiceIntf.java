package com.cts.customer.service;

import com.cts.customer.model.Customer;
import com.cts.customer.model.CustomerCreationStatus;

//Methods of Customer Microservice's service layer containing business logic 
public interface CustServiceIntf {
	
	//1. To add new Customer
	public CustomerCreationStatus addCustomer(Customer customer,String requestTokenHeader);
	
	//2. To get Customer Details
	public Customer getCustomerDetails(Integer custId);
}
