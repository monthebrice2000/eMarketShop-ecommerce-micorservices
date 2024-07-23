package com.emarketshop.shipping_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.shipping_service.domain.OrderItem;
import com.emarketshop.shipping_service.domain.id.OrderItemId;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
	
	
	
}
