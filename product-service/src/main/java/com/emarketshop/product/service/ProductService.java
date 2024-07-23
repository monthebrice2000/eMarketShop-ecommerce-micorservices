package com.emarketshop.product.service;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

import com.emarketshop.product.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> findAll() throws TimeoutException;
	ProductDto findById(final Integer productId, final Locale locale);
	ProductDto save(final ProductDto productDto);
	ProductDto update(final ProductDto productDto);
	// ProductDto update(final Integer productId, final ProductDto productDto);
	ProductDto update(final Integer productId, final ProductDto productDto, final Locale locale);
	// void deleteById(final Integer productId);
	void deleteById(final Integer productId, final Locale locale);
	
}
