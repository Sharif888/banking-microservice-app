package com.cts.rules.service;

import com.cts.rules.model.RuleStatus;

//Methods of Rules Microservice's service layer containing business logic 
public interface RulesServiceIntf {
	
	//1. To evaluate minimum balance condition
	public RuleStatus evaluateMinBalance(Double balance, Double accountId);
	
	//2. To get service charge on a Transaction
	public Double getServiceCharge(Double amount);
	
}
