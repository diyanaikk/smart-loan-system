package com.example.payment.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="repayments")
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanId;

    private Long userId;

    private Double totalAmount;

    private Double emiAmount;

    private LocalDate dueDate;

    private String status;

    public Repayment() {}

    public Repayment(Long loanId, Long userId,
                     Double totalAmount,
                     Double emiAmount,
                     LocalDate dueDate,
                     String status) {

        this.loanId = loanId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.emiAmount = emiAmount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() { return id; }

    public Long getLoanId() { return loanId; }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(Double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}