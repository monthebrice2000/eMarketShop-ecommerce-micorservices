package com.emarketshop.order_service.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.emarketshop.order_service.dto.OrderDto;
import com.emarketshop.order_service.exception.wrapper.OrderNotFoundException;
import com.emarketshop.order_service.helper.OrderMappingHelper;
import com.emarketshop.order_service.repository.OrderRepository;
import com.emarketshop.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	@Autowired
	MessageSource messages;

	private final OrderRepository orderRepository;

	@Override
	public List<OrderDto> findAll() {
		log.info("*** OrderDto List, service; fetch all orders *");
		return this.orderRepository.findAll().stream().map(OrderMappingHelper::map).distinct()
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public OrderDto findById(final Integer orderId, final Locale locale) {
		log.info("*** OrderDto, service; fetch order by id *");
		return this.orderRepository.findById(orderId).map(OrderMappingHelper::map)
				.orElseThrow(() -> new OrderNotFoundException(
						String.format(messages.getMessage("order.findbyid.error.message", null, locale), orderId)));

	}

	@Override
	public OrderDto save(final OrderDto orderDto) {
		log.info("*** OrderDto, service; save order *");
		return OrderMappingHelper.map(this.orderRepository.save(OrderMappingHelper.map(orderDto)));
	}

	@Override
	public OrderDto update(final OrderDto orderDto) {
		log.info("*** OrderDto, service; update order *");
		return OrderMappingHelper.map(this.orderRepository.save(OrderMappingHelper.map(orderDto)));
	}

	@Override
	public OrderDto update(final Integer orderId, final OrderDto orderDto, final Locale locale) {
		log.info("*** OrderDto, service; update order with orderId *");
		return OrderMappingHelper
				.map(this.orderRepository.save(OrderMappingHelper.map(this.findById(orderId, locale))));
	}

	@Override
	public void deleteById(final Integer orderId, final Locale locale) {
		log.info("*** Void, service; delete order by id *");
		this.orderRepository.delete(OrderMappingHelper.map(this.findById(orderId, locale)));
	}

}
