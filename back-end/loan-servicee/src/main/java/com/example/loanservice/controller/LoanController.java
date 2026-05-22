//this 	file handles all HTTP requests
//talks to service layer

package com.example.loanservice.controller;

import com.example.loanservice.entity.Loan;
import com.example.loanservice.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.*;

@RestController
//converts the class to REST API
@RequestMapping("/loans")
//base URL
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Apply Loan
    //for creating loan
    @Operation(summary="apply for a loan here")
    @PostMapping("/apply")
    //this method will accept loan data from request
    public ResponseEntity<?> applyLoan(@Valid @RequestBody Loan loan) {
    	//ResponseEntity<?>: this is used to control: response body + status code
        try {
            Loan savedLoan = loanService.applyLoan(loan);
            return ResponseEntity.ok(savedLoan);
            //for success request: returns 200 OK + loan
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            //if error: returns 400 BAD REQUEST + error message
        }
    }
    //@RequestBody-> takes JSON input

    // Get all loans
    @Operation(summary="get ALL loans")
    @GetMapping
    //fetching loan
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    // Get loans by user
    @Operation(summary="get loans by UserID")
    @GetMapping("/user/{userId}")
    //GET /loans/user/5
    //extracts userId from URL, then returns loan for that user
    //@PathVariable -> takes value from URL
    public List<Loan> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUserId(userId);
    }
    
    //update
    @Operation(summary="update loan status")
    @PutMapping("/{id}/status")
    public Loan updateStatus(@PathVariable Long id, @RequestParam String status) {
        return loanService.updateLoanStatus(id, status);
        //PUT /loans/10/status?status=APPROVED
        //id-> from path
        //status-> from query param
    }
    
  
    
    //@RequestParam will take value from query
    //?status=APPROVED → status= APPROVED
}