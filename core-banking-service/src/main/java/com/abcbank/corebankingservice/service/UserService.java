package com.abcbank.corebankingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import com.abcbank.corebankingservice.entity.Customer;
import com.abcbank.corebankingservice.repository.CustomerRepository;
import com.abcbank.corebankingservice.dto.CustomerDto;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
    }

    public void registerUser(CustomerDto customerDto){
        // Existing registration logic...
    }
}