package com.cts.accountmanagement.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Feign Client communication name and url for rules microservice
@FeignClient(name = "rules", url = "${rule.URL}")
public interface RuleClient {

	//1. Mapping with get service charge method of Rules Microservice
	@GetMapping(value="/getServiceCharge/{amount}")
	public Double getServiceCharge(@PathVariable("amount") Double amount);

}
