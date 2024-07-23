package com.emarketshop.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.payment_service.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	
	
}
