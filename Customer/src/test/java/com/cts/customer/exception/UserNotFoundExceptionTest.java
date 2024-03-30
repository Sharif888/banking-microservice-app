package com.cts.customer.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UserNotFoundExceptionTest {

	private UserNotFoundException userNotFoundException;
	
	@Test
	public void testing()
	{
		userNotFoundException=new UserNotFoundException();
		assertThrows(UserNotFoundException.class, ()->{ throw new UserNotFoundException(); });
	}
}
