package com.emarketshop.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.user_service.domain.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
	
	
	
}
