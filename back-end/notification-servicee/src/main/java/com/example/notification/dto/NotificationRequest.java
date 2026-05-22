package com.example.notification.dto;

import jakarta.validation.constraints.*;

public class NotificationRequest {
	@NotNull(message="UserID is required")
	private Long userId;
	@NotBlank(message="Message cannot be empty")
	private String message;
	
	public NotificationRequest() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
