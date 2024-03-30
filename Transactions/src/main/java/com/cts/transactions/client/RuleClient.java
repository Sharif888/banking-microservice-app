package com.cts.transactions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.transactions.model.RuleStatus;

//Feign Client communication name and url for rules microservice
@FeignClient(name = "rules", url = "${rule.URL}")
public interface RuleClient {
	
	//1. Mapping with evaluate minimum balance method of Rules Microservice
	@GetMapping(value="/evaluateminbal/{balance}/{amount}")
	public RuleStatus calculateMinimumBalance(@PathVariable("balance") Double balance, @PathVariable("amount") Double amount);

}
