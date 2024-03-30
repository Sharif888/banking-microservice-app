package com.cts.customer.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UnauthorizedAccessExceptionTest{

	private UnauthorizedAccessException unauthorizedAccessException;
	
	@Test
	public void testing()
	{
		unauthorizedAccessException=new UnauthorizedAccessException();
		assertThrows(UnauthorizedAccessException.class, ()->{ throw new UnauthorizedAccessException(); });
	}
}
