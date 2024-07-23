package com.emarketshop.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.user_service.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	
	
}
