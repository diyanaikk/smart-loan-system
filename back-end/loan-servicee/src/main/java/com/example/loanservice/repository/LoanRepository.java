package com.example.loanservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.loanservice.entity.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Custom method
    List<Loan> findByUserId(Long userId);  //SELECT * FROM loan WHERE user_id = ?
    int countByUserId(Long userId);  // to count how many loans users have
    //SELECT COUNT(*) FROM loan WHERE user_id = ?
}

//Loan-> entity
//long-> type (primary key type)

//this repo connects the app to DB
//-> and also handles CRUD