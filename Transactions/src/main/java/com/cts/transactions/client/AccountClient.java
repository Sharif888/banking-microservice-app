package com.cts.transactions.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.transactions.model.Account;
import com.cts.transactions.model.TransactionStatus;

//Feign Client communication name and url for account management microservice
@FeignClient(name = "account-management", url = "${acc.URL}")
public interface AccountClient {
	
	//1. Mapping with deposit method of Account Management
	@PostMapping(value = "/deposit/{accId}/{amt}")
	public TransactionStatus deposit(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@PathVariable("accId")Integer accId,@PathVariable("amt")Double amount);
	
	//2. Mapping with withdraw method of Account Management
	@PostMapping(value = "/withdraw/{accId}/{amt}")
	public TransactionStatus withdraw(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,@PathVariable("accId")Integer accId,@PathVariable("amt")Double amount);
	
	//3.. Mapping with get balance method of Account Management
	@GetMapping("/getbalance/{accId}")
	public Double getBalance(@PathVariable("accId") Integer accId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);

	//4. Mapping with get customer id method of Account Management
	@GetMapping("/getcustomerid/{accId}")
	public Integer getCustomerId(@PathVariable("accId") Integer accId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
	
	//5. Mapping with get account details method of Account Management
	@GetMapping("/getaccount/{accId}")
	public Account getAccount(@PathVariable("accId") Integer accId,@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
	

}