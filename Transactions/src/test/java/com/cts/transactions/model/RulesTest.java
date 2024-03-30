package com.cts.transactions.model;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cts.transactions.model.RuleStatus;
import org.junit.jupiter.api.Test;

import com.cts.transactions.model.RuleStatus;

class RulesTest {

	RuleStatus rules = new RuleStatus();
	RuleStatus rules1 = new RuleStatus("Denied");

	@Test
	void setStatusTest() {
		rules.setStatus("Allowed");
		assertEquals("Allowed", rules.getStatus());
	}
	@Test
	void getStatusTest()
	{
		rules.setStatus("Denied");
		assertTrue(rules.getStatus().equals("Denied"));
	}

}
