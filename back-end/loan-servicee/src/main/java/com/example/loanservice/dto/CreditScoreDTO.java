//data transfer object 
//used to transfer data btw services


//Loan Service →calls → Credit Score Service

//this will receive response like:
/*{
  		"userId": 1,
  		"score": 75,
  		"status": "GOOD",
  		"risk": "LOW"
}*/



package com.example.loanservice.dto;

public class CreditScoreDTO {
	private Long userId;
	private int score;
	private String status;
	private String risk;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk=risk;
	}
}


//used in service where i wrote: 
		//CreditScoreDTO cs = restTemplate.getForObject(creditUrl, CreditScoreDTO.class);
