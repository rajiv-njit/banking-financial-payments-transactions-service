package com.abcbank.paymenthandling.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;

import com.abcbank.paymenthandling.model.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentHandlingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAccounts() throws Exception {
        // You may mock the RestTemplate call for isolation in tests
        mockMvc.perform(get("/api/payments"))
               .andExpect(status().isOk())
               .andExpect(content().string("Payment processed. Accounts: List of bank accounts"));
    }

    @Test
    public void testProcessPayment() throws Exception{
        //Create a paymentRequest object
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAccount("123456");
        paymentRequest.setBankName("Bank ABC");
        paymentRequest.setFromAccount("123456");
        paymentRequest.setToAccount("654321");
        paymentRequest.setAmount(100.00);

    //Convert PaymentRequest object to JSON
    String paymentRequestJson = objectMapper.writeValueAsString(paymentRequest);

    //Perform a POST request
    mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(paymentRequestJson))
               .andExpect(status().isOk())
               .andExpect(content().string("Payment processed: List of bank accounts"));

}
}
