package com.example.payment.dto;

import jakarta.validation.constraints.*;

public class PaymentRequest {
	@NotNull(message="UsreID is required")
	private Long userId;
	@NotNull(message="Amount is required")
	@Min(value=1, message="Amount must be greater than 0")
	private Double amount;
	
	private Long loanId;

	private Integer tenure;
	
	public PaymentRequest() {
		
	}
	public PaymentRequest(Long userId, Double amount, Long loanId, Integer tenure) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.loanId = loanId;
        this.tenure = tenure;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }
	
	
}
