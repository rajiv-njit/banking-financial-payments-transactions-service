package com.example.corebankingservice.entity;

import org.springframework.stereotype.Indexed;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String fullName;
    
}
