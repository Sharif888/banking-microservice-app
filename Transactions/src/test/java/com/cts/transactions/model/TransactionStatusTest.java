package com.cts.transactions.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TransactionStatusTest {
		TransactionStatus transactionStatus = new TransactionStatus();
	
		@Test
		void setAccIdTest() {
			transactionStatus.setAccId(1);
			assertEquals(1, transactionStatus.getAccId());
		}
		@Test
		void setBalanceTest()
		{
			transactionStatus.setBalance(1000.0);
			assertEquals(1000.0, transactionStatus.getBalance());
		}
		@Test
		void setMessageTest()
		{
			transactionStatus.setMessage("SUCESS");
			assertEquals("SUCESS", transactionStatus.getMessage());
		}
}
