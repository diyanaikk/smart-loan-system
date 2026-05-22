//calculate and fetch credit score

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CreditScore;
import com.example.demo.service.CreditScoreService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Validated
@RequestMapping("/credit")
public class CreditScoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditScoreController.class);
	//to track requests
	
	@Autowired
	private CreditScoreService service;
	
	@Operation(summary="calculate credit score")
	@PostMapping("/calculate")
	public CreditScore calculate(@RequestParam @NotNull (message = "UserId is required") long userId,
								 @RequestParam  @Min(value = 1000, message = "Salary must be at least 1000") double salary,
								 @RequestParam @Min(value = 0, message = "Loan count cannot be negative") int loanCount) {
		//POST /credit/calculate?userId=1&salary=50000&loanCount=2
		logger.info("Received request to calculate credit score for userId: {}", userId);
		
		//@RequestParam: get values from query parameters in the URL (e.x. /users?age=20 →age)
		return service.calculateScore(userId, salary, loanCount);
	}
	
	@Operation(summary="get credit score by UserID")
	@GetMapping("/{userId}")
	//@PathVAriable: get values from URL path itself(/users/20 → 20)
	public CreditScore getScore(@PathVariable Long userId) {
		logger.info("Fetching credit score for userId: {}", userId);
		return service.getScore(userId);
	}
	
	@Operation(summary="update credit score")
	@PutMapping("/update/{userId}")

	public CreditScore updateScore(

	        @PathVariable Long userId,

	        @RequestParam int score

	) {

	    logger.info(
	        "Updating credit score for userId: {}",
	        userId
	    );

	    return service.updateScore(
	        userId,
	        score
	    );

	}
}
