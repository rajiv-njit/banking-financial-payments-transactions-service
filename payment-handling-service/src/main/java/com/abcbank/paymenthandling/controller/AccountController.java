package com.abcbank.paymenthandling.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping
    // Commenting out PreAuthorize for temporary access
    // @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getAccounts() {
        // In a real implementation, fetch and return accounts from the database
        return "List of bank accounts"; // Placeholder response
    }
}
