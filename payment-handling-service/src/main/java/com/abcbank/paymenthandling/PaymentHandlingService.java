package com.abcbank.paymenthandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Main entry point for the Payment Handling Service application. 
//This class bootstraps the Spring Boot application.
@SpringBootApplication
public class PaymentHandlingService {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHandlingService.class, args);
    }
}
