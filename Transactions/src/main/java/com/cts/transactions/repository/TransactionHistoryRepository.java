package com.cts.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.transactions.model.TransactionHistory;

//Transaction History Repository for database connectivity using JPA
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

}