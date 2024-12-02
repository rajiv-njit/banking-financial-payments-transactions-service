package com.abcbank.paymenthandling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.abcbank.paymenthandling.entity.Customer;
import com.abcbank.paymenthandling.repository.CustomerRepository;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            customer.getUsername(),
            customer.getPassword(), // Store the plain password for testing
            new ArrayList<>()); // No authorities for simplicity
    }

    @Override
    public boolean validateUser(String username, String password) {
    Customer customer = customerRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
    // Directly compare the provided plaintext password for testing purposes
    if (customer.getPassword().equals(password)) {
        return true; // Return true if the passwords match
    }
    return false; // Return false if they do not match
}
}