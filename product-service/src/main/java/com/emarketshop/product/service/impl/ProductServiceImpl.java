package com.emarketshop.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.emarketshop.product.constant.ActionEnum;
import com.emarketshop.product.dto.ProductDto;
// import com.emarketshop.product.events.source.SimpleSourceBean;
import com.emarketshop.product.exception.wrapper.ProductNotFoundException;
import com.emarketshop.product.helper.ProductMappingHelper;
import com.emarketshop.product.repository.ProductRepository;
import com.emarketshop.product.service.ProductService;
import com.emarketshop.product.utils.UserContextHolder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	MessageSource messages;

	// @Autowired
	// SimpleSourceBean simpleSourceBean;

	private final ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	@CircuitBreaker(name = "productService", fallbackMethod = "buildFallbackProductList")
	@Bulkhead(name = "bulkheadProductService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "buildFallbackProductList")
	@Retry(name = "retryProductService", fallbackMethod = "buildFallbackProductList")
	public List<ProductDto> findAll() throws TimeoutException {
		log.info("*** ProductDto List, service; fetch all products *",
				UserContextHolder.getContext().getCorrelationId());
		randomlyRunLong();
		return this.productRepository.findAll()
				.stream()
				.map(ProductMappingHelper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}

	private List<ProductDto> buildFallbackProductList(Throwable t) {
		List<ProductDto> fallbackList = new ArrayList<>();
		ProductDto productDto = new ProductDto();
		productDto.setProductId(0000000 - 00 - 00000);
		// license.setOrganizationId(organizationId);
		productDto.setProductTitle("Sorry no licensing information currently available");
		fallbackList.add(productDto);
		return fallbackList;
	}

	@Override
	public ProductDto findById(final Integer productId, final Locale locale) {
		log.info("*** ProductDto, service; fetch product by id *");
		return this.productRepository.findById(productId)
				.map(ProductMappingHelper::map)
				.orElseThrow(() -> new ProductNotFoundException(
						String.format(messages.getMessage("product.findbyid.error.message", null, locale), productId)));
	}

	@Override
	public ProductDto save(final ProductDto productDto) {
		log.info("*** ProductDto, service; save product *");

		ProductDto productDtoSaved = ProductMappingHelper.map(this.productRepository
				.save(ProductMappingHelper.map(productDto)));

		// simpleSourceBean.publishProductChange(
		// 		ActionEnum.CREATED,
		// 		String.format("%d", productDtoSaved.getProductId()) );
		return productDtoSaved;
	}

	@Override
	public ProductDto update(final ProductDto productDto) {
		log.info("*** ProductDto, service; update product *");
		return ProductMappingHelper.map(this.productRepository
				.save(ProductMappingHelper.map(productDto)));
	}

	@Override
	public ProductDto update(final Integer productId, final ProductDto productDto, final Locale locale) {
		log.info("*** ProductDto, service; update product with productId *");
		return ProductMappingHelper.map(this.productRepository
				.save(ProductMappingHelper.map(this.findById(productId, locale))));
	}

	@Override
	public void deleteById(final Integer productId, final Locale locale) {
		log.info("*** Void, service; delete product by id *");
		this.productRepository.delete(ProductMappingHelper
				.map(this.findById(productId, locale)));
	}

	private void randomlyRunLong() throws TimeoutException {
		Random rand = new Random();
		int randomNum = rand.nextInt(3) + 1;
		if (randomNum == 3)
			sleep();
	}

	private void sleep() throws TimeoutException {
		try {
			Thread.sleep(5000);
			throw new java.util.concurrent.TimeoutException();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}

}
