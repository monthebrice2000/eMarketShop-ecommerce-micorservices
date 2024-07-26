package com.emarketshop.order_service.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.order_service.dto.CartDto;

public interface CartService {
	
	List<CartDto> findAll();
	// CartDto findById(final Integer cartId);
	CartDto findById(final Integer cartId, final Locale locale);
	CartDto save(final CartDto cartDto);
	CartDto update(final CartDto cartDto);
	// CartDto update(final Integer cartId, final CartDto cartDto);
	CartDto update(final Integer cartId, final CartDto cartDto, final Locale locale);
	void deleteById(final Integer cartId);
	
}
