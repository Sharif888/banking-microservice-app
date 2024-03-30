package com.cts.transactions.model;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class  TransactionHistoryTest {

	TransactionHistory transaction = new TransactionHistory();
//	TransactionHistory transaction2 = new TransactionHistory("TRANSFER",1,10000,2,5000.0,LocalDate.now(),"Completed",10000.0,25.0);

	@Test
	void setTransactionIdTest() {
		transaction.setTransactionId(1);
		assertEquals(1, transaction.getTransactionId());
	}

	@Test
	void setSourceAccountIdTest() {
		
		transaction.setSourceAccountId(1);
		assertEquals(1, transaction.getSourceAccountId());
	}

	

	@Test
	void DestinationAccountIdTest() {
		transaction.setDestinationAccountId(1);
		;
		assertEquals(1, transaction.getDestinationAccountId());
	}

	@Test
	void setAmountTest() {
		transaction.setAmount(1000);
		assertEquals(1000, transaction.getAmount());
	}

	@Test
	void setTransactionTypeTest() {
		transaction.setTransactionType("Deposit");
		assertEquals("Deposit", transaction.getTransactionType());
	}

	@Test
	void setDateOfTransactionTest() {
		transaction.setDateOfTransaction(null);
		assertEquals(null, transaction.getDateOfTransaction());
	}
	
	
	@Test
	void setServiceChargeTest()
	{
		transaction.setServiceCharge(20.0);
		assertEquals(20.0, transaction.getServiceCharge());
	}
	@Test
	void setTransactionStatusTest()
	{
		transaction.setTransactionStatus("Completed");
		assertEquals("Completed", transaction.getTransactionStatus());
	}
	@Test
	void setBalanceTest()
	{
		transaction.setBalance(10000.0);
		assertEquals(10000.0, transaction.getBalance());
	}
}

