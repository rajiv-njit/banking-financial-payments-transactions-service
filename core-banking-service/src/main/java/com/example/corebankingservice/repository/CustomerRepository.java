package com.example.corebankingservice.repository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);
    
}
