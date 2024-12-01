package com.abcbank.paymenthandling.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.abcbank.paymenthandling.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username); // Method for finding user by username
}