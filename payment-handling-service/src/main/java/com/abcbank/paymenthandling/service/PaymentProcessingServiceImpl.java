package com.abcbank.paymenthandling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abcbank.paymenthandling.dto.PaymentRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


// Implementation of the PaymentProcessingService interface. 
// Contains logic to process payments by interacting with the Core Banking Service and validates JWT tokens
@Service
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String processPayment(PaymentRequest paymentRequest) {
        // Retrieve JWT token from the authenticating service
        String token = getJwtToken(); // Placeholder for actual token retrieval logic

        // Include the JWT in headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        // Prepare the payment request to forward to the Core Banking Service
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8080/api/accounts", // Call to retrieve account info, adjust as needed
            org.springframework.http.HttpMethod.POST, // Use the appropriate method
            entity,
            String.class
        );

        return "Payment processed: " + responseEntity.getBody(); // Use the received response
    }

    // This is a placeholder for actual JWT retrieval logic
    private String getJwtToken() {
        return "your_jwt_token_here"; // Replace with actual logic to obtain token
    }
}