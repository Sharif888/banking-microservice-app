package com.cts.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Main Application Class of Account Management Microservice
@SpringBootApplication
@EnableFeignClients
public class AccountManagementApplication {

	//Main method of Account Management Microservice
	public static void main(String[] args) {
		SpringApplication.run(AccountManagementApplication.class, args);
	}

}
