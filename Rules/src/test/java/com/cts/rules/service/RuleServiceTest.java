package com.cts.rules.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.cts.rules.model.RuleStatus;
import com.cts.rules.service.RulesServiceIntfImpl;

public class RuleServiceTest {
	
		RulesServiceIntfImpl rulesService = new RulesServiceIntfImpl();
		
		
		@Test
		public void evalMinBalance_allowed()
		{
			RuleStatus evaluateMinBalance = rulesService.evaluateMinBalance(20000.0, 2000.0);
			String status = evaluateMinBalance.getStatus();
			assertEquals("allowed", status);
		}
		@Test
		public void evalMinBalance_denied()
		{
			RuleStatus evaluateMinBalance = rulesService.evaluateMinBalance(10.0, 2000.0);
			String status = evaluateMinBalance.getStatus();
			assertEquals("denied", status);
		}
		
		@Test
		public void serviceChargeTest()
		{
		Double serviceCharge = rulesService.getServiceCharge(10000.0);
		assertEquals(250, serviceCharge);
		
		}
		
}
