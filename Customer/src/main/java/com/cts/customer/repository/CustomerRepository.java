package com.cts.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.customer.model.Customer;

//Customer Repository for database connectivity using JPA
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
 