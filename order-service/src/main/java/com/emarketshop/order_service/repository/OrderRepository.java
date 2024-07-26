package com.emarketshop.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.order_service.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	
	
}
