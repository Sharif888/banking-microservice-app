package com.cts.transactions.model;

import static org.junit.jupiter.api.Assertions.*;


import com.cts.transactions.model.Account;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


class AccountTest {

	
	Account account = new Account();
	Account account1 = new Account(10,10000.0,"Saving");

	@Test
	void setAccIdTest() {
		account.setAccId(1);
		assertEquals(1, account.getAccId());
	}

	@Test
	void setCustomerIdTest() {
		account.setCustId(1);
		assertEquals(1, account.getCustId());
	}

	@Test
	void setCurrentBalanceTest() {
		account.setBalance(5000.0);
		assertEquals(5000.0, account.getBalance());
	}

	@Test
	void setAccTypeTest() {
		account.setAccType("Savings");
		assertEquals("Savings", account.getAccType());
	}


	

	@Test
	void getAccTest() {
		account.setAccId(1);
		assertTrue(account.getAccId() == 1);
	}

	@Test
	void getCustomerTest() {
		account.setCustId(1);
		assertEquals(1, account.getCustId());
	}

	@Test
	void getAcctypeTest() {
		account.setAccType("abc");
		assertTrue(account.getAccType() == "abc");
	}

	@Test
	void getTokenTest() {
		account.setBalance(10.0);
		assertTrue(account.getBalance() == 10.0);
	}

	
}
