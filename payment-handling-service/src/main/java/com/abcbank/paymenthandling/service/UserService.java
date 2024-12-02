package com.abcbank.paymenthandling.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean validateUser(String username, String password);
}