package com.example.paymenthandling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/payments")
public class PaymentHandlingController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String processPayment() {
        String response = restTemplate.getForObject("http://localhost:8080/api/accounts", String.class);
        return "Payment processed. Accounts: " + response;
    }
}
