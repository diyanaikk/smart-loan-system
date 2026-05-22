package com.example.demo.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CreditScore;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore,Long>{
	Optional<CreditScore> findByUserId(Long userid);
}
