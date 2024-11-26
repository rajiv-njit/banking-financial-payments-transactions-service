package com.example.paymenthandling.controller;

//CR-01 : Update the PaymentHandlingController to use PaymentRequest
import com.example.paymenthandling.PaymentRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    // CR-01 : PostMapping for processing payments
    @PostMapping
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        System.out.println("Processing Payment From " + paymentRequest.getFromAccount() + 
                           " to " + paymentRequest.getToAccount() + 
                           " via bank " + paymentRequest.getBankName());
        
        // Use RestTemplate to retrieve account information
        String response = restTemplate.getForObject("http://localhost:8080/api/accounts", String.class);

        // Return a confirmation message with the correct casing
        return "Payment processed: " + response; // Adjusted casing to match expected output
    }
}
