package com.emarketshop.order_service.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.order_service.dto.OrderDto;

public interface OrderService {
	
	List<OrderDto> findAll();
	// OrderDto findById(final Integer orderId);
	OrderDto findById(final Integer orderId, final Locale locale);
	OrderDto save(final OrderDto orderDto);
	OrderDto update(final OrderDto orderDto);
	// OrderDto update(final Integer orderId, final OrderDto orderDto);
	OrderDto update(final Integer orderId, final OrderDto orderDto, final Locale locale);
	// void deleteById(final Integer orderId);
	void deleteById(final Integer orderId, final Locale locale);
	
}
