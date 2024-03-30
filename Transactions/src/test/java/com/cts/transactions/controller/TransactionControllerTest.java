package com.cts.transactions.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.transactions.client.AuthorizingClient;
import com.cts.transactions.model.TransactionHistory;
import com.cts.transactions.model.TransactionStatus;
import com.cts.transactions.service.TransactionServiceIntf;



@WebMvcTest
public class TransactionControllerTest {
	
	@MockBean
	private AuthorizingClient authorizingClient;
	
	@MockBean
	private TransactionServiceIntf transactionService;
	private String token = "Bearer token";
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void depositValidTest() throws Exception
	{
		TransactionStatus status = new TransactionStatus(1,10000.0,"SUCCESS");
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(transactionService.deposit(token, 1, 1000.0)).thenReturn(status);
		this.mockMvc.perform(post("/deposit")
				.header("Authorization", token)
		.param("accId", "1")
        .param("amount", "1000.0"))
		
			.andExpect(jsonPath("$.accId").value(1))
		.andExpect(jsonPath("$.balance").value(10000.0))
		.andExpect(jsonPath("$.message").value("SUCCESS"));
	}
	
	@Test
	public void withdrawValidTest() throws Exception
	{
		TransactionStatus status = new TransactionStatus(1,10000.0,"SUCCESS");
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(transactionService.withdraw(token, 1, 1000.0)).thenReturn(status);
		this.mockMvc.perform(post("/withdraw")
				.header("Authorization", token)
				.param("accId", "1")
		        .param("amount", "1000.0"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.accId").value(1))
		.andExpect(jsonPath("$.balance").value(10000.0))
		.andExpect(jsonPath("$.message").value("SUCCESS"));
	}
	
	
	@Test
	public void transferValidTest() throws Exception
	{
		TransactionStatus status = new TransactionStatus(1,10000.0,"SUCCESS");
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		when(transactionService.transfer(token, 1,2, 1000.0)).thenReturn(status);
		this.mockMvc.perform(post("/transfer")
				.header("Authorization", token)
				.param("accIdSource", "1")
				.param("accIdDest", "2")
		        .param("amount", "1000.0"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.accId").value(1))
		.andExpect(jsonPath("$.balance").value(10000.0))
		.andExpect(jsonPath("$.message").value("SUCCESS"));
	}
	
	
	@Test
	public void transactionTest() throws Exception
	{
		TransactionHistory transactionHistory = new TransactionHistory("Withdraw",1,1,2,1000.0,LocalDateTime.now(),"SUCESS",10000.0,250.0);
		TransactionHistory transactionHistory1 = new TransactionHistory("Withdraw",1,1,2,1000.0,LocalDateTime.now(),"SUCESS",10000.0,250.0);
		List<TransactionHistory> list = new ArrayList<>();
		list.add(transactionHistory);
		list.add(transactionHistory1);
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(true);
		
		when(transactionService.transaction(token,1)).thenReturn(list);
		this.mockMvc.perform(get("/transactionlist")
						.header("Authorization", token)
				        .param("custId", "1"))
		  .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	void depositInvalid() throws Exception
	{
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(false);
		this.mockMvc.perform(post("/deposit")
				.header("Authorization", token)
				.param("accId", "1")
		        .param("amt", "1000.0"))
			.andExpect(status().isBadRequest());
	}
	@Test
	void withdrawInvalid() throws Exception
	{
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(false);
		this.mockMvc.perform(post("/withdraw")
				.header("Authorization", token)
				.param("accId", "1")
		        .param("amt", "1000.0"))
		.andExpect(status().isBadRequest());
	}
	@Test
	void transferInvalid() throws Exception
	{
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(false);
		this.mockMvc.perform(post("/transfer")
				.header("Authorization", token)
				.param("sourceAccId", "1")
				.param("targetedAccId", "2")
		        .param("amt", "1000.0"))
		.andExpect(status().isBadRequest());
	}
	@Test
	void transactionInvalid() throws Exception
	{
		when(authorizingClient.authorizeTheRequest(token)).thenReturn(false);
		this.mockMvc.perform(get("/transactionlist")
				.header("Authorization", token)
		        .param("custid", "1"))
		.andExpect(status().isBadRequest());
	}
	

}
