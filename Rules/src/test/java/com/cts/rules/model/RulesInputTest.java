package com.cts.rules.model;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class RulesInputTest {	
  RuleStatus accInp=new RuleStatus();
	
	@Test
	void setStatusTest() {
		accInp.setStatus("allowed");
		assertEquals("allowed", accInp.getStatus());
	}
	
	@Test
	void getStatusTest() {
		accInp.setStatus("denied");
		equals(accInp.getStatus().equals("denied"));
	}

}
