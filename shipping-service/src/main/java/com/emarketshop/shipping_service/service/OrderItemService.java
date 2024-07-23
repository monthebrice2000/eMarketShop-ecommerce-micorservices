package com.emarketshop.shipping_service.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.shipping_service.domain.id.OrderItemId;
import com.emarketshop.shipping_service.dto.OrderItemDto;

public interface OrderItemService {
	
	List<OrderItemDto> findAll();
	// OrderItemDto findById(final OrderItemId orderItemId);
	OrderItemDto findById(final OrderItemId orderItemId, final Locale locale);
	OrderItemDto save(final OrderItemDto orderItemDto);
	OrderItemDto update(final OrderItemDto orderItemDto);
	void deleteById(final OrderItemId orderItemId);
	
}
