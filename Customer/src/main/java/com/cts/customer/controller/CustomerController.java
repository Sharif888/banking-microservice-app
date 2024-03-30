package com.cts.customer.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer.clients.AuthorizingClient;
import com.cts.customer.exception.UnauthorizedAccessException;
import com.cts.customer.model.Customer;
import com.cts.customer.model.CustomerCreationStatus;
import com.cts.customer.service.CustServiceIntf;

//Customer Controller class 
@RestController
@CrossOrigin
public class CustomerController {
	
	//Configuring logger
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	//Autowired - Authorizing Microservice's Client
	@Autowired
	private AuthorizingClient authorizingClient;
	
	//Autowired - Customer Service Interface object
	@Autowired
	private CustServiceIntf custServiceIntf;
	
	//Post mapping with create customer route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Customer Object containing customer details -Request Body 
	 * @Returns customer creation status
	 */
	@PostMapping(value = "/createcustomer")
	public CustomerCreationStatus createCustomer(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,Customer customer) {
		logger.info("Inside Create Customer");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			//Adding new customer
			CustomerCreationStatus status = custServiceIntf.addCustomer(customer,requestTokenHeader);
			logger.info("Exiting Create Customer");
			
			//Returning customer creation status
			return status;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
	
	//Get mapping with get customer details route 
	/* 
	 * @@@@Parameters@@@@
	 * @Auth Token Header
	 * @Path Variable - customer id
	 * @Returns Customer Object with customer details
	 */
	@GetMapping("/getcustomerdetails/{custId}")
	public Customer getCustomerDetails(@PathVariable("custId") int custId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
	{
		logger.info("Inside getCustomerDetails");
		
		//Verifying user token
		boolean authResponse = authorizingClient.authorizeTheRequest(requestTokenHeader);
		
		if (authResponse==true) {
			//Getting customer details by calling feign client method
			Customer customer = custServiceIntf.getCustomerDetails(custId);
			logger.info("Exiting getCustomerDetails");
			
			//Returning customer object
			return customer;
		}
		else
		{
			//Error Thrown
			logger.debug("User Unauthorized");
			throw new UnauthorizedAccessException();
		}
	}
}
