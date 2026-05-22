package com.example.notification.service;

import com.example.notification.dto.*;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

	public String sendNotification(NotificationRequest request) {
		String message=request.getMessage();
		Long userId=request.getUserId();
		
		if(message!=null && message.toLowerCase().contains("approved")) {
			String emailLog ="Email sent to User "+userId+": Your loan is approved!";
			logger.info("EMAIL sent to User {}: Your loan is approved!", userId);
			
			return emailLog;
		}
		else if(message!=null &&message.toLowerCase().contains("rejected")) {
			String smsLog="SMS sent to User "+userId+": Your loan is rejected.";
			logger.info("SMS sent to User {}: Your loan is rejected.", userId);
			
			return smsLog;
		}
		else {
			String genericLog="Notification sent to User "+userId+": "+message;
			logger.info("Notification sent to User {}: {}", userId, message);
			//example: under review of loan(generic notifications)
			return genericLog;
		}
	}
}
