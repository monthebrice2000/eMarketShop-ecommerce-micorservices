package com.emarketshop.order_service.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emarketshop.order_service.dto.OrderDto;
import com.emarketshop.order_service.dto.response.collection.DtoCollectionResponse;
import com.emarketshop.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderResource {
	
	private final OrderService orderService;
	
	@GetMapping
	public ResponseEntity<DtoCollectionResponse<OrderDto>> findAll() {
		log.info("*** OrderDto List, controller; fetch all orders *");
		return ResponseEntity.ok(new DtoCollectionResponse<>(this.orderService.findAll()));
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> findById(
			@PathVariable("orderId") 
			@NotBlank(message = "Input must not be blank") 
			@Valid final String orderId,
			@RequestHeader(value = "Accept-Language",required = false) Locale locale) {
		log.info("*** OrderDto, resource; fetch order by id *");
		return ResponseEntity.ok(this.orderService.findById(Integer.parseInt(orderId), locale));
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> save(
			@RequestBody 
			@NotNull(message = "Input must not be NULL") 
			@Valid final OrderDto orderDto) {
		log.info("*** OrderDto, resource; save order *");
		return ResponseEntity.ok(this.orderService.save(orderDto));
	}
	
	@PutMapping
	public ResponseEntity<OrderDto> update(
			@RequestBody 
			@NotNull(message = "Input must not be NULL") 
			@Valid final OrderDto orderDto) {
		log.info("*** OrderDto, resource; update order *");
		return ResponseEntity.ok(this.orderService.update(orderDto));
	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<OrderDto> update(
			@PathVariable("orderId")
			@NotBlank(message = "Input must not be blank")
			@Valid final String orderId,
			@RequestBody 
			@NotNull(message = "Input must not be NULL") 
			@Valid final OrderDto orderDto,
			@RequestHeader(value = "Accept-Language",required = false) Locale locale) {
		log.info("*** OrderDto, resource; update order with orderId *");
		return ResponseEntity.ok(this.orderService.update(Integer.parseInt(orderId), orderDto, locale));
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Boolean> deleteById(
			@PathVariable("orderId") final String orderId, 
			@RequestHeader(value = "Accept-Language",required = false) Locale locale) {
		log.info("*** Boolean, resource; delete order by id *");
		this.orderService.deleteById(Integer.parseInt(orderId), locale);
		return ResponseEntity.ok(true);
	}
	
	
	
}










