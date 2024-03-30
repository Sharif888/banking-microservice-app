package com.cts.transactions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//Feign Client communication name and url for Authorization microservice
@FeignClient(name = "Authorizatiion-Microservice", url = "${auth.URL}")
public interface AuthorizingClient {
	
	//1. Mapping with authorize method of Authorization microservice to validate user
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) ;
}