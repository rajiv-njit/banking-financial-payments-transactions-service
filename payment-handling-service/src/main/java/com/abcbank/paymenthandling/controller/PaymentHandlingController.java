package com.abcbank.paymenthandling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.abcbank.paymenthandling.dto.PaymentRequest;
import com.abcbank.paymenthandling.dto.AuthenticationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/payments")
public class PaymentHandlingController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        System.out.println("Processing Payment From " + paymentRequest.getFromAccount() + 
                           " to " + paymentRequest.getToAccount() + 
                           " via bank " + paymentRequest.getBankName());

        // Prepare the authentication request to log in and retrieve a valid JWT token
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setUsername("testuser"); // Replace with appropriate logged-in user's username
        authRequest.setPassword("testpassword"); // Replace with appropriate password, if using fixed test data
        
        String token = null;

        try {
            // Convert the AuthenticationRequest object to JSON
            String authRequestJson = objectMapper.writeValueAsString(authRequest);
        
            // Call the login endpoint to retrieve JWT token
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/api/login", 
                    new HttpEntity<>(authRequestJson, getHttpHeaders()), String.class);
        
            token = response.getBody(); // Obtaining the JWT token from the response
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Log the exception (consider a proper logging mechanism)
            return "Error processing authentication request: " + e.getMessage(); // Return error response to user
        }

        // Now use this token to authenticate your payment processing request
        if (token != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            // Prepare the payment request to forward to the Core Banking Service
            HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/api/accounts", // Call to Core Banking Service
                HttpMethod.POST,
                entity,
                String.class
            );

            return "Payment processed: " + responseEntity.getBody(); // Use the received response
        } else {
            return "Failed to retrieve JWT token"; // Handle case where token retrieval fails
        }
    }

    // Helper method to create HTTP headers
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }
}