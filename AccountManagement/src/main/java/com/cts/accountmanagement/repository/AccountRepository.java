package com.cts.accountmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.accountmanagement.model.Account;

//Account Repository for database connectivity using JPA
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
 