package com.cts.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.accountmanagement.model.Statement;

//Statement Repository for database connectivity using JPA
public interface StatementRepository extends JpaRepository<Statement, Integer> {

}