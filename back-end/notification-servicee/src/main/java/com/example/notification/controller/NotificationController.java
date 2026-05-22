//handles notification requests from other services

package com.example.notification.controller;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/notify")
public class NotificationController {
	
	private static final Logger logger= LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	private NotificationService service;
	
	@Operation(summary="send notification to user")
	@PostMapping
	public String sendNotification(@Valid @RequestBody NotificationRequest request) {
		//POST /notify
		/*
		 * { 
				 * "userId": 1, 
				 * "message": "Loan APPROVED" 
		 * }
		 */
		//converts JSOn to NotificationRequest obj
	    logger.info("Received notification request for userId: {}", request.getUserId());
		return service.sendNotification(request);
		//service will decide what to do(sms/mail)
		//@RequestBody-> to take JSON input
	}
}
