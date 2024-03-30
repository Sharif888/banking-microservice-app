package com.cts.customer.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.customer.model.AccountCreationStatus;

//Feign Client communication name and url for account management microservice
@FeignClient(name = "account-management", url = "${acc.URL}")
public interface AccountClient {
	
	//1. Mapping with create account method of Account Management
	@PostMapping(value = "/createaccount/{custId}")
	public List<AccountCreationStatus> createCustomerAccs(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@PathVariable("custId") Integer custId);
}