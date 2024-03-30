package com.cts.transactions.exceptions;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Global Exception Handler Class inheriting Response Entity Exception handler
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	//Handling User not found exceptions
	@ExceptionHandler(UserNotFoundException.class) 
	public void  springHandleUserNotFound(HttpServletResponse response) throws IOException{
	  response.sendError(HttpStatus.BAD_REQUEST.value(), "Sorry!! No User with this ID"); 
	}
	
	//Handling No Transaction History exceptions
	@ExceptionHandler(NoTransactionHistoryException.class) 
	public void  springHandleNoTransactionHistory(HttpServletResponse response) throws IOException{
	  response.sendError(HttpStatus.BAD_REQUEST.value(), "No Transaction History"); 
	}
	
	//Handling Unauthorized Access exceptions
	@ExceptionHandler(UnauthorizedAccessException.class) 
	public void  springHandleUnauthorizedAccess(HttpServletResponse response) throws IOException{
	  response.sendError(HttpStatus.BAD_REQUEST.value(), "You are unauthorized"); 
	}
	
	//Handling Account Not Found exceptions
	@ExceptionHandler(AccountNotFoundException.class) 
	public void  springHandleAccountNotFound(HttpServletResponse response) throws IOException{
	  response.sendError(HttpStatus.BAD_REQUEST.value(), "Sorry!! No Account with this ID"); 
	}
	
	//Handling Insufficient Balance exceptions
	@ExceptionHandler(InsufficientBalanceException.class) 
	public void  springHandleInsufficientBalance(HttpServletResponse response) throws IOException{
	  response.sendError(HttpStatus.BAD_REQUEST.value(), "Cannot withdraw!! Insufficient Balance"); 
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
        Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
	}
	
}