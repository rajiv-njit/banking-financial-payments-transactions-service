package com.abcbank.paymenthandling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.abcbank.paymenthandling.dto.PaymentRequest;


// Controller class that handles payment-related requests. 
// It processes payment submissions and interacts with the Core Banking Service for account information.
@RestController
@RequestMapping("/api/payments")
public class PaymentHandlingController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        System.out.println("Processing Payment From " + paymentRequest.getFromAccount() + 
                           " to " + paymentRequest.getToAccount() + 
                           " via bank " + paymentRequest.getBankName());
        
        // Retrieve JWT token before processing the payment
        String token = getJwtToken(); // Placeholder for actual token retrieval logic
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Include the JWT token in the headers
        
        // Make REST call to Core Banking Service to validate account information
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8080/api/accounts", // Call to Core Banking Service
            HttpMethod.GET,
            entity,
            String.class
        );

        return "Payment processed: " + responseEntity.getBody(); // Return the response from Core Banking
    }

    // This method simulates a JWT token retrieval.
    private String getJwtToken() {
        // Ideally, you would perform a login here and retrieve the token by making a POST request to the login endpoint
        return "your_jwt_token_here"; // Placeholder for the actual token
    }
}