package com.abcbank.corebankingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.corebankingservice.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);
    
}
