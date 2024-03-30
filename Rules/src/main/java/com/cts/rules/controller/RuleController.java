package com.cts.rules.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.rules.model.RuleStatus;
import com.cts.rules.service.RulesServiceIntf;

//Rule Controller Class
@RestController
public class RuleController {

	//Configuring Logger
	Logger logger = LoggerFactory.getLogger(RuleController.class);
	
	//Autowired - Rules Service Interface object
	@Autowired
	private RulesServiceIntf ruleService;
	
	//Get mapping to evaluate minimum balance route 
	/* 
	 * @@@@Parameters@@@@
	 * @Path Variable - Balance in account
	 * @Path Variable - Amount to be withdrawn
	 * @Returns RuleStatus String as "allowed" or "declined"
	 */
	@GetMapping(value="/evaluateminbal/{balance}/{amount}")
	public RuleStatus calculateMinimumBalance(@PathVariable("balance") Double balance, @PathVariable("amount") Double amount){
		logger.info("Checking Min Bal in Rule Microservice");
		
		//Calling evaluate minimum balance from service layer and returning the status
		RuleStatus status = ruleService.evaluateMinBalance(balance, amount);
		return status;
		
	}
	
	//Get mapping to get service charge route 
	/* 
	 * @@@@Parameters@@@@
	 * @Path Variable - Amount to be withdrawn
	 * @Returns service charge(Double)
	 */
	@GetMapping(value="/getServiceCharge/{amount}")
	public Double getServiceCharge(@PathVariable("amount") Double amount){
		logger.info("Calculating Service Charge in Rule Microservice");
		
		//Calling get service charge from service layer and returning the charge
		Double charge = ruleService.getServiceCharge(amount);
		return charge;
		
	}
}
