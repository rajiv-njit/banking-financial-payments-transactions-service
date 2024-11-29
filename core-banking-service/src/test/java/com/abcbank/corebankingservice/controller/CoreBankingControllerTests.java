package com.abcbank.corebankingservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CoreBankingController.class)
public class CoreBankingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
               .andExpect(status().isOk());
    }
}