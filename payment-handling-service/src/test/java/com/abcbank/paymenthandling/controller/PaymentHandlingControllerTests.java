package com.abcbank.paymenthandling.controller;

import com.abcbank.paymenthandling.dto.AuthenticationRequest;
import com.abcbank.paymenthandling.dto.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentHandlingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Disabled
    @Test
    public void testProcessPayment() throws Exception {
        // Login request details
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setUsername("testuser"); // Set your test username
        authRequest.setPassword("testpassword"); // Set your test password

        // Convert login request to JSON
        String authRequestJson = objectMapper.writeValueAsString(authRequest);
        
        // Perform login and get JWT token
        String jwtToken = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authRequestJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(); // Extract the JWT token from the response

        // Create a PaymentRequest object
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAccount("123456");
        paymentRequest.setBankName("Bank ABC");
        paymentRequest.setFromAccount("123456");
        paymentRequest.setToAccount("654321");
        paymentRequest.setAmount(100.00);

        // Convert PaymentRequest object to JSON
        String paymentRequestJson = objectMapper.writeValueAsString(paymentRequest);

        // Perform a POST request to process the payment with the JWT token
        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtToken) // Include the JWT token in the header
                .content(paymentRequestJson))
               .andExpect(status().isOk())
               .andExpect(content().string("Payment processed: List of bank accounts")); // Adjust based on actual service response
    }
}