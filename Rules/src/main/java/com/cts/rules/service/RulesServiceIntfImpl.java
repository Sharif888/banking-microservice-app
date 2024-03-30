package com.cts.rules.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.cts.rules.model.RuleStatus;

//Implementation of the Rule Service Interface
//Marked as Service annotation for the automatic definition of beans
@Service
public class RulesServiceIntfImpl implements RulesServiceIntf
{
	//Configuring Logger
	Logger logger = LoggerFactory.getLogger(RulesServiceIntfImpl.class);
	
	//Overridden evaluate minimum balance method of Rule Interface
	/*
	 * @@@@Arguments@@@@
	 * @Balance in the account
	 * @Amount to be withdrawn 
	 * @Returns status as - "allowed" or "declined"
	 */
	@Override
	public RuleStatus evaluateMinBalance(Double balance, Double amount) {
		
		/*Validations of negative amount & Maintaining minimum balance conditions 
		 * is met or not if withdrawal happens (Considering deduction of service charge 
		 * as well of 2.5%)
		 * 
		 * @Return
		 * 1. "denied" - If conditions are not met
		 * 2. "allowed" - If conditions are met 
		 */
		if(amount<=0 || (balance-amount-0.025*amount) < 10000) {
			logger.info("Denied in Rule Microservice");
			return new RuleStatus("denied");
		}
		else {
			logger.info("Allowed in Rule Microservice");
			return new RuleStatus("allowed");
		}
	}

	//Overridden get service charge method of Rule Interface
	/*
	 * @@@@Arguments@@@@
	 * @Amount to be withdrawn 
	 * @Returns service charge
	 */
	@Override
	public Double getServiceCharge(Double amount) {
		logger.info("Returning service charge in Rule Microservice");
		return 0.025*amount;
	}

}
