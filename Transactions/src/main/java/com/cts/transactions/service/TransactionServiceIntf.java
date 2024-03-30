package com.cts.transactions.service;

import java.util.List;
import com.cts.transactions.model.TransactionHistory;
import com.cts.transactions.model.TransactionStatus;

//Methods of Transaction Microservice's service layer containing business logic
public interface TransactionServiceIntf {
	
	//1. To deposit money
	public TransactionStatus deposit(String requestTokenHeader,Integer accId,Double amount);
	
	//2. To withdraw money
	public TransactionStatus withdraw(String requestTokenHeader,Integer accId,Double amount);
	
	//3. To transfer money
	public TransactionStatus transfer(String requestTokenHeader,Integer accIdSource,Integer accIdDest,Double amount);
	
	//4. To get transaction history
	public List<TransactionHistory> transaction(String requestTokenHeader,Integer custId);
	
}
