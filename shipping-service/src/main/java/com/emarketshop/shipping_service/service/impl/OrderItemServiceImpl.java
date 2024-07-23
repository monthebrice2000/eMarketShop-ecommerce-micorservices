package com.emarketshop.shipping_service.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emarketshop.shipping_service.constant.AppConstant;
import com.emarketshop.shipping_service.domain.id.OrderItemId;
import com.emarketshop.shipping_service.dto.OrderDto;
import com.emarketshop.shipping_service.dto.OrderItemDto;
import com.emarketshop.shipping_service.dto.ProductDto;
import com.emarketshop.shipping_service.exception.wrapper.OrderItemNotFoundException;
import com.emarketshop.shipping_service.helper.OrderItemMappingHelper;
import com.emarketshop.shipping_service.repository.OrderItemRepository;
import com.emarketshop.shipping_service.service.OrderItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	MessageSource messages;

	private final OrderItemRepository orderItemRepository;
	private final RestTemplate restTemplate;

	@Override
	public List<OrderItemDto> findAll() {
		log.info("*** OrderItemDto List, service; fetch all orderItems *");
		return this.orderItemRepository.findAll().stream().map(OrderItemMappingHelper::map).map(o -> {
			o.setProductDto(this.restTemplate.getForObject(
					AppConstant.DiscoveredDomainsApi.PRODUCT_SERVICE_API_URL + "/" + o.getProductDto().getProductId(),
					ProductDto.class));
			o.setOrderDto(this.restTemplate.getForObject(
					AppConstant.DiscoveredDomainsApi.ORDER_SERVICE_API_URL + "/" + o.getOrderDto().getOrderId(),
					OrderDto.class));
			return o;
		}).distinct().collect(Collectors.toUnmodifiableList());
	}

	@Override
	public OrderItemDto findById(final OrderItemId orderItemId, final Locale locale) {
		log.info("*** OrderItemDto, service; fetch orderItem by id *");
		return this.orderItemRepository.findById(null).map(OrderItemMappingHelper::map).map(o -> {
			o.setProductDto(this.restTemplate.getForObject(
					AppConstant.DiscoveredDomainsApi.PRODUCT_SERVICE_API_URL + "/" + o.getProductDto().getProductId(),
					ProductDto.class));
			o.setOrderDto(this.restTemplate.getForObject(
					AppConstant.DiscoveredDomainsApi.ORDER_SERVICE_API_URL + "/" + o.getOrderDto().getOrderId(),
					OrderDto.class));
			return o;
		}).orElseThrow(() -> new OrderItemNotFoundException(
				String.format(messages.getMessage("orderitem.findbyid.error.message", null, locale), orderItemId)));

	}

	@Override
	public OrderItemDto save(final OrderItemDto orderItemDto) {
		log.info("*** OrderItemDto, service; save orderItem *");
		return OrderItemMappingHelper.map(this.orderItemRepository.save(OrderItemMappingHelper.map(orderItemDto)));
	}

	@Override
	public OrderItemDto update(final OrderItemDto orderItemDto) {
		log.info("*** OrderItemDto, service; update orderItem *");
		return OrderItemMappingHelper.map(this.orderItemRepository.save(OrderItemMappingHelper.map(orderItemDto)));
	}

	@Override
	public void deleteById(final OrderItemId orderItemId) {
		log.info("*** Void, service; delete orderItem by id *");
		this.orderItemRepository.deleteById(orderItemId);
	}

}
