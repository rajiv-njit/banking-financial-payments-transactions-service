package com.abcbank.paymenthandling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.abcbank.paymenthandling.Util.JwtUtil;
import com.abcbank.paymenthandling.dto.AuthenticationRequest;

// Manages user login requests. 
// It validates user credentials and generates JWT tokens for authenticated users.
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // Authenticate credentials
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        // Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // Generate JWT token
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt); // Return the JWT token
    }
}