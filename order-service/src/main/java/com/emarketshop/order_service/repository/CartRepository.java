package com.emarketshop.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.order_service.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	
	
}
