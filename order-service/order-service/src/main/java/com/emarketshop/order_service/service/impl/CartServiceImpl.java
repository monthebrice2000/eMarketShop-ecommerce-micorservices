package com.emarketshop.order_service.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emarketshop.order_service.constant.AppConstant;
import com.emarketshop.order_service.dto.CartDto;
import com.emarketshop.order_service.dto.UserDto;
import com.emarketshop.order_service.exception.wrapper.CartNotFoundException;
import com.emarketshop.order_service.helper.CartMappingHelper;
import com.emarketshop.order_service.repository.CartRepository;
import com.emarketshop.order_service.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	@Autowired
	MessageSource messages;
	
	private final CartRepository cartRepository;
	private final RestTemplate restTemplate;
	
	@Override
	public List<CartDto> findAll() {
		log.info("*** CartDto List, service; fetch all carts *");
		return this.cartRepository.findAll()
				.stream()
					.map(CartMappingHelper::map)
					.map(c -> {
						c.setUserDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
								.USER_SERVICE_API_URL + "/" + c.getUserDto().getUserId(), UserDto.class));
						return c;
					})
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public CartDto findById(final Integer cartId, final Locale locale) {
		log.info("*** CartDto, service; fetch cart by id *");
		return this.cartRepository.findById(cartId)
				.map(CartMappingHelper::map)
				.map(c -> {
					c.setUserDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
							.USER_SERVICE_API_URL + "/" + c.getUserDto().getUserId(), UserDto.class));
					return c;
				})
				.orElseThrow(() -> new CartNotFoundException(String
						.format(messages.getMessage("cart.findbyid.error.message", null,locale), cartId)));
						
	}
	
	@Override
	public CartDto save(final CartDto cartDto) {
		log.info("*** CartDto, service; save cart *");
		return CartMappingHelper.map(this.cartRepository
				.save(CartMappingHelper.map(cartDto)));
	}
	
	@Override
	public CartDto update(final CartDto cartDto) {
		log.info("*** CartDto, service; update cart *");
		return CartMappingHelper.map(this.cartRepository
				.save(CartMappingHelper.map(cartDto)));
	}
	
	@Override
	public CartDto update(final Integer cartId, final CartDto cartDto, final Locale locale) {
		log.info("*** CartDto, service; update cart with cartId *");
		return CartMappingHelper.map(this.cartRepository
				.save(CartMappingHelper.map(this.findById(cartId, locale))));
	}
	
	@Override
	public void deleteById(final Integer cartId) {
		log.info("*** Void, service; delete cart by id *");
		this.cartRepository.deleteById(cartId);
	}
	
	
	
}










