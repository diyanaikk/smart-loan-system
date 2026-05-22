//receives payment requests


package com.example.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.PaymentRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.payment.service.*;
import jakarta.validation.*;

import io.swagger.v3.oas.annotations.Operation;

import org.slf4j.Logger;

@RestController
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService service;

	@Operation(summary="process the payment")
    @PostMapping("/pay")
    //POST /pay
    public String makePayment(@Valid @RequestBody PaymentRequest request) {
		/*
		 * { 
				 * "userId": 1, 
				 * "amount": 50000 
		 * }
		 */
        logger.info("Received payment request for userId: {}", request.getUserId());

        return service.processPayment(request);
    }
}
