package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
//marks class as DB table	
public class CreditScore {
	@Id   //primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//tells DB to auto generate primary key
	private Long id;
	
	private Long userId;
	private int score;
	private int totalLoans;
	private double salary;
	private String status; //Good ? Average ? poor
	private String risk;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public int getTotalLoans() {
		return totalLoans;
	}
	public void setTotalLoans(int totalLoans) {
		this.totalLoans = totalLoans;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
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
