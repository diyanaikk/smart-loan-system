package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.*;

import com.example.demo.entity.CreditScore;
import com.example.demo.repository.CreditScoreRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
//marks class as Service layer, where all business logic is written
public class CreditScoreService {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditScoreService.class);
	
	@Autowired
	private CreditScoreRepository repo;
	//save and fetch data from DB
	public CreditScore calculateScore(Long userId, double salary, int loanCount) {
		logger.info("Calculating credit score for userId: {}", userId);
		int score=0;
		
		if(salary>50000) {
			score+=50;
		}
		else if(salary>30000) {
			score+=30;
		}
		else {
			score+=10;
		}
		
		if(loanCount==0) {
			score+=40;
		}
		else if(loanCount<3) {
			score+=20;
		}
		else {
			score+=5;
		}
		
		String status;
		String risk;
		
		if(score>=80) {
			status="GOOD";
			risk="LOW";
		}
		else if(score>=50) {
			status="AVERAGE";
			risk="MEDIUM";
		}
		else {
			status="POOR";
			risk="HIGH";
		}
		
		//fetching or creating record
		CreditScore cs= repo.findByUserId(userId).orElse(new CreditScore());
		//if exists then update
		//else create new
		cs.setUserId(userId);
		cs.setSalary(salary);
		cs.setTotalLoans(loanCount);
		cs.setScore(score);
		cs.setStatus(status);
		cs.setRisk(risk);
		
		logger.info("Score calculated for userId: {}, salary: {}, loanCount: {}", userId, salary, loanCount);
		
		return repo.save(cs);
	}
	
	public CreditScore getScore(Long userId) {
		logger.info("Credit score saved for userId: {}", userId);
		return repo.findByUserId(userId).orElse(null);
		//fetch from db
	}
	
	public CreditScore updateScore(Long userId, int newScore) {

	    CreditScore cs =
	        repo.findByUserId(userId)
	            .orElse(null);

	    if(cs != null) {

	        cs.setScore(newScore);

	        // update status/risk dynamically
	        if(newScore >= 80) {

	            cs.setStatus("GOOD");
	            cs.setRisk("LOW");

	        }
	        else if(newScore >= 50) {

	            cs.setStatus("AVERAGE");
	            cs.setRisk("MEDIUM");

	        }
	        else {

	            cs.setStatus("POOR");
	            cs.setRisk("HIGH");

	        }

	        logger.info(
	            "Updating score for userId: {} to {}",
	            userId,
	            newScore
	        );

	        return repo.save(cs);

	    }

	    return null;

	}
}
