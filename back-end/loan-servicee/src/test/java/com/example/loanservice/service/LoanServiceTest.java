package com.example.loanservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//assertion and mockito methods

import java.util.HashMap;
import java.util.Map;

import com.example.loanservice.dto.CreditScoreDTO;
import com.example.loanservice.entity.Loan;
import com.example.loanservice.repository.LoanRepository;

import org.junit.jupiter.api.BeforeEach;
//runs before every test
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.web.client.RestTemplate;

public class LoanServiceTest {

    @Mock
    private LoanRepository repo;
    //fake repo

    @Mock
    private RestTemplate restTemplate; //mock external calls

    @InjectMocks
    private LoanService service; //real LoanService 
    //injects mocked repo and restTemplate

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }  //runs before every test & initializes all mocks

    @Test
    void testApplyLoan() {
    	//test method starts
        Loan loan = new Loan(1L, 50000, 12, "PENDING");
        //creates sample loan object

        // MOCK USER SERVICE CALL
        Map<String, Object> fakeUser = new HashMap<>(); //fake response data
        fakeUser.put("salary", 50000);  //adds salary into fake user response

        when(restTemplate.getForObject(contains("users"), eq(Map.class)))
                .thenReturn(fakeUser);
        //whenever API call contains "users"
        //return fake user data
        
        //mock credit service
        CreditScoreDTO cs = new CreditScoreDTO();
        cs.setScore(80); // to make it APPROVED

        when(restTemplate.getForObject(contains("credit"), eq(CreditScoreDTO.class)))
                .thenReturn(cs);
        
        //mock notification call
        when(restTemplate.postForObject(contains("notify"), any(), eq(String.class)))
        .thenReturn("SMS sent");

        //MOCK SAVE
        when(repo.save(any(Loan.class))).thenReturn(loan);
        //whenever any Loan object is saved,return loan object
        Loan saved = service.applyLoan(loan);

        assertNotNull(saved); //check if obj exists
        assertEquals("APPROVED", saved.getStatus());  //checks loan got approved
    }
}



//loanService depends on external services
//so in unit testing I mocked RestTemplate 
//using Mockito to isolate business logic.