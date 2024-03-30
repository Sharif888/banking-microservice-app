package com.cts.rules.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.cts.rules.model.RuleStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.cts.rules.service.RulesServiceIntf;

@WebMvcTest
public class RuleControllerTest {
	@InjectMocks
	RuleController ruleController;

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private RulesServiceIntf ruleService;
	
	@Test
	void calMinBalanceTestMVC() throws Exception {
		RuleStatus ruleStatus = new RuleStatus();
		ruleStatus.setStatus("allowed");
		when( ruleService.evaluateMinBalance(20000.0,1000.0)).thenReturn(ruleStatus);
		this.mockMvc.perform(get("/evaluateminbal/{balance}/{amount}", 20000.0, 1000.0))
				.andExpect(jsonPath("$.status").value("allowed"));
	}
	
	@Test
	void getServiceChargeTest() throws Exception
	{
		when(ruleService.getServiceCharge(10000.0)).thenReturn(250.0);
		this.mockMvc.perform(get("/getServiceCharge/{amount}",10000.0)).
		andExpect(jsonPath("$").value(250.0));
	}
//	@Test
//	void getServiceTest() throws Exception
//	{
//		when(ruleService.getServiceCharge(10000.0)).thenReturn(250.0);
//		this.mockMvc.perform(get("/getServiceCharge/{amount}",10000.0)).
//		andExpect(jsonPath("$").value(250.0));
//	}

}
