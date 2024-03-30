package com.cts.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Main Application Class of Transaction Microservice
@SpringBootApplication
@EnableFeignClients
public class TransactionsApplication {

	//Main method of Transaction Microservice
	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

}
