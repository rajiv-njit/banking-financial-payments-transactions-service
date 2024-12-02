package com.abcbank.paymenthandling.controller;

import com.abcbank.paymenthandling.dto.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentHandlingControllerTests {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests in tests

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for JSON conversion

    // @Autowired
    // private JwtUtil jwtUtil; // Inject JwtUtil to generate a valid token

    @Test
public void testProcessPayment() throws Exception {
    PaymentRequest paymentRequest = new PaymentRequest();
    paymentRequest.setAccount("123456");
    paymentRequest.setBankName("Bank ABC");
    paymentRequest.setFromAccount("123456");
    paymentRequest.setToAccount("654321");
    paymentRequest.setAmount(100.00);

    String paymentRequestJson = objectMapper.writeValueAsString(paymentRequest);
    String token = "mockJwtToken"; // Example token for testing

    // Perform POST request to process the payment
    mockMvc.perform(post("/api/payments")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token) // Mock token for testing
            .content(paymentRequestJson))
            .andExpect(status().isOk()) // Expect successful processing
            .andExpect(content().string("Payment processed: List of bank accounts")); // Adjust as per actual response
}
}