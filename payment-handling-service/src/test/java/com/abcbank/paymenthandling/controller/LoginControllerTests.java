package com.abcbank.paymenthandling.controller;

import com.abcbank.paymenthandling.dto.AuthenticationRequest;
import com.abcbank.paymenthandling.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @Autowired
    private ObjectMapper objectMapper;

    // Assuming your userService.validateUser now returns boolean
    @Test
public void testLogin() throws Exception {
    AuthenticationRequest authRequest = new AuthenticationRequest();
    authRequest.setUsername("rajiv");
    authRequest.setPassword("123456"); // Use the plaintext password directly

    // Mock the user retrieval in UserService
    when(userService.loadUserByUsername("rajiv")).thenReturn(new org.springframework.security.core.userdetails.User(
        "rajiv",
        "123456", // This matches the database entry
        new ArrayList<>()
    ));

    String authRequestJson = objectMapper.writeValueAsString(authRequest);

    // Execute login request
    mockMvc.perform(post("/api/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(authRequestJson))
            .andExpect(status().isOk()); // Expect 200 OK response
}
}