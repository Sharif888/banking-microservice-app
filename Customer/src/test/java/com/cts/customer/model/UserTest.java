package com.cts.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

	private User user;
	
	
	@Test
	public void testGetter() {
		user=new User();
		user.setCustId(1);
		user.setId(1);
		user.setPassword("sharath");
		user.setRole("Employee");
		user.setUserName("sharath");
		assertEquals(1, user.getCustId());
		assertEquals(1, user.getId());
		assertEquals("sharath", user.getPassword());
		assertEquals("sharath", user.getUserName());
		assertEquals("Employee", user.getRole());
	}
	
	@Test
	public void testSetter() {
		user=new User();
		user.setCustId(1);
		user.setId(1);
		user.setPassword("sharath");
		user.setRole("Employee");
		user.setUserName("sharath");
		assertEquals(1, user.getCustId());
		assertEquals(1, user.getId());
		assertEquals("sharath", user.getPassword());
		assertEquals("sharath", user.getUserName());
		assertEquals("Employee", user.getRole());
	}
}
