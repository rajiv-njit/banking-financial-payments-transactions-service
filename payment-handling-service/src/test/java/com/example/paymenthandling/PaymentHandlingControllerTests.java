package com.example.paymenthandling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentHandlingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProcessPayment() throws Exception {
        // You may mock the RestTemplate call for isolation in tests
        mockMvc.perform(get("/api/payments"))
               .andExpect(status().isOk())
               .andExpect(content().string("Payment processed. Accounts: List of bank accounts"));
    }
}
