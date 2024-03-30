package com.cts.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Main Application Class of Customer Microservice
@SpringBootApplication
@EnableFeignClients
public class CustomerApplication {

	//Main method of Customer Microservice
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
