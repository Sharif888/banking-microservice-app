package com.cts.customer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.cts.customer.model.User;

//Feign Client communication name and url for Authorization microservice
@FeignClient(name = "Authorizatiion-Microservice", url = "${auth.URL}")
public interface AuthorizingClient {
	
	//Mapping with authorize method of Authorization microservice to validate user
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
	
	//Mapping with add user method of Authorization microservice to add new user
	@PostMapping(value = "/adduser")
	public void addUser(@RequestBody User user);
}