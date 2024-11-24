package com.example.corebankingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CoreBankingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
               .andExpect(status().isOk())
               .andExpect(content().string("List of bank accounts"));
    }
}
