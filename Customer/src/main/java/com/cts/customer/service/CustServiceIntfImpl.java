package com.cts.customer.service;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.customer.clients.AccountClient;
import com.cts.customer.clients.AuthorizingClient;
import com.cts.customer.exception.UserNotFoundException;
import com.cts.customer.model.Customer;
import com.cts.customer.model.CustomerCreationStatus;
import com.cts.customer.model.User;
import com.cts.customer.repository.CustomerRepository;

//Implementation of the Customer Service Interface
//Marked as Service annotation for the automatic definition of beans
@Service
public class CustServiceIntfImpl implements CustServiceIntf{

	//Configuring logger
	Logger logger = LoggerFactory.getLogger(CustServiceIntfImpl.class);
	
	//Autowired - Customer Repository variable
	@Autowired
	CustomerRepository custRepo;
	
	//Autowired - Account Microservice's Client
	@Autowired
	AccountClient accClient;
	
	//Autowired - Authorization Microservice's Client
	@Autowired
	AuthorizingClient authClient;
	
	//Overridden add Customer method of Customer Interface
	/*
	 * @@@@Arguments@@@@
	 * @Customer Object containing customer details 
	 * @Returns customer creation status
	 */
	@Override
	public CustomerCreationStatus addCustomer(Customer customer,String requestTokenHeader) {
		logger.info("Adding Customer in Customer Microservice");
		
		//Finding Customer using Customer Id
		Optional<Customer> newCust = custRepo.findById(customer.getCustId());
		
		//Checking existence of customer of same user id
		if(newCust.isPresent()) {
			logger.info("Customer already there in Customer Microservice");
			
			//Returning Status as - User Id Already Exists
			return new CustomerCreationStatus(customer.getCustId(),"User Id Already Exists");
		}
		else
		{
			int flag=0;
			logger.info("Extracting list to check if cust already present in Customer Microservice");
			
			/*  Finding all the existing customers to check for already
			 *  existing user name
			 */
			List<Customer> custList = custRepo.findAll();
			
			//Looping through all the customers list
			for(Customer cust:custList)
			{
				if(cust.getCustUname().compareTo(customer.getCustUname())==0)
				{
					flag=1;break;
				}
			}
			
			//Found User with same name
			if(flag==1)
			{
				logger.info("User Name Already Taken in Customer Microservice");
				
				//Returning Status as - User name already taken
				return new CustomerCreationStatus(customer.getCustId(),"User Name Already Taken");
			}
			
			logger.info("Saving customer in Customer Microservice");
			
			//Saving new customer and adding new user 
			custRepo.save(customer);
			authClient.addUser(new User(customer.getCustUname(),customer.getCustPass(),customer.getCustId(),"Customer"));
			accClient.createCustomerAccs(requestTokenHeader, customer.getCustId());
			
			//Returning user created successfully status
			return new CustomerCreationStatus(customer.getCustId(),"User Created Successfully");
		}
	}
		
	//Overridden Get Customer Details method of Customer Interface
	/*
	 * @@@@Arguments@@@@
	 * @Customer Id 
	 * @Returns customer object
	 */
	@Override
	public Customer getCustomerDetails(Integer custId) {
		
		logger.info("Getting Customer in Customer Microservice");

		//Finding customer using the id passed
		Optional<Customer> customer = custRepo.findById(custId);
		
		//Checking for existence of the customer and returning customer details
		if(customer.isPresent()) {
			return customer.get();
		}
		else {
			//Throwing User not found exception
			logger.info("Customer not present in Customer Microservice");
			throw new UserNotFoundException();
		}
		
	}

}
