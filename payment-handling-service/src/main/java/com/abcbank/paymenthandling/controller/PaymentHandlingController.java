package com.abcbank.paymenthandling.controller;

import com.abcbank.paymenthandling.model.PaymentRequest; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/payments")
public class PaymentHandlingController {

    @Autowired
    private RestTemplate restTemplate;

    // This method simulates a JWT token retrieval:
    private String getJwtToken() {
        // Ideally, you would perform a login here and retrieve the token.
        // For example, making a POST request to the login endpoint.
        return "your_jwt_token_here"; // Placeholder for the actual token
    }

    @PostMapping
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        System.out.println("Processing Payment From " + paymentRequest.getFromAccount() + 
                           " to " + paymentRequest.getToAccount() + 
                           " via bank " + paymentRequest.getBankName());

        // Retrieve JWT token
        String token = getJwtToken(); // Replace with actual logic to obtain token

        // Include the JWT in headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Use RestTemplate to call Core Banking Service
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8080/api/accounts", // Ensure this endpoint is correct
            HttpMethod.GET,
            entity,
            String.class
        );

        return "Payment processed: " + responseEntity.getBody(); // Use the received response
    }
}