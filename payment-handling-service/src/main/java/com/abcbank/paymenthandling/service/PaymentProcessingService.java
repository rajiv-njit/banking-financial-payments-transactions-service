package com.abcbank.paymenthandling.service;

import com.abcbank.paymenthandling.dto.PaymentRequest;


// Interface defining methods for processing payments. 
// Outlines functionalities to be implemented by its concrete class.
public interface PaymentProcessingService {
    String processPayment(PaymentRequest paymentRequest);
}
