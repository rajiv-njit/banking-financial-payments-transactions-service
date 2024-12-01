package com.abcbank.paymenthandling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentHandlingServiceConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Create a RestTemplate bean for making HTTP requests
    }
}
