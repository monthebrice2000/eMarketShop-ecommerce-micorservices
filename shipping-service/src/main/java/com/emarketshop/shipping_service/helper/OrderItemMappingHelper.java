package com.emarketshop.shipping_service.helper;

import com.emarketshop.shipping_service.domain.OrderItem;
import com.emarketshop.shipping_service.dto.OrderDto;
import com.emarketshop.shipping_service.dto.OrderItemDto;
import com.emarketshop.shipping_service.dto.ProductDto;

public interface OrderItemMappingHelper {
	
	public static OrderItemDto map(final OrderItem orderItem) {
		return OrderItemDto.builder()
				.productId(orderItem.getProductId())
				.orderId(orderItem.getOrderId())
				.orderedQuantity(orderItem.getOrderedQuantity())
				.productDto(
						ProductDto.builder()
							.productId(orderItem.getProductId())
							.build())
				.orderDto(
						OrderDto.builder()
							.orderId(orderItem.getOrderId())
							.build())
				.build();
	}
	
	public static OrderItem map(final OrderItemDto orderItemDto) {
		return OrderItem.builder()
				.productId(orderItemDto.getProductId())
				.orderId(orderItemDto.getOrderId())
				.orderedQuantity(orderItemDto.getOrderedQuantity())
				.build();
	}
	
	
	
}










