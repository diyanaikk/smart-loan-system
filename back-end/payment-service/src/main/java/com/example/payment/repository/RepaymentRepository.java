package com.example.payment.repository;

import com.example.payment.entity.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository
        extends JpaRepository<Repayment, Long> {

    List<Repayment> findByUserId(Long userId);
}