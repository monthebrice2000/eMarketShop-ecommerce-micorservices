package com.emarketshop.shipping_service.resource;

import jakarta.validation.Valid;
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

import com.emarketshop.shipping_service.domain.id.OrderItemId;
import com.emarketshop.shipping_service.dto.OrderItemDto;
import com.emarketshop.shipping_service.dto.ProductDto;
import com.emarketshop.shipping_service.dto.response.collection.DtoCollectionResponse;
import com.emarketshop.shipping_service.service.OrderItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/shippings")
@Slf4j
@RequiredArgsConstructor
public class OrderItemResource {

	private final OrderItemService orderItemService;

	@GetMapping
	public ResponseEntity<DtoCollectionResponse<OrderItemDto>> findAll() {
		log.info("*** OrderItemDto List, controller; fetch all orderItems *");
		return ResponseEntity.ok(new DtoCollectionResponse<>(this.orderItemService.findAll()));
	}

	@GetMapping("/{orderId}/{productId}")
	public ResponseEntity<OrderItemDto> findById(
			@PathVariable("orderId") final String orderId,
			@PathVariable("productId") final String productId,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		log.info("*** OrderItemDto, resource; fetch orderItem by id *");

		OrderItemDto orderItemDto = this.orderItemService.findById(
				new OrderItemId(Integer.parseInt(orderId), Integer.parseInt(productId)), locale);
		orderItemDto.add(
				linkTo(methodOn(OrderItemResource.class).findById(orderId, productId, locale)).withSelfRel(),
				linkTo(methodOn(OrderItemResource.class).save(orderItemDto)).withRel("save"),
				linkTo(methodOn(OrderItemResource.class).update(orderItemDto)).withRel("update"),
				linkTo(methodOn(OrderItemResource.class).deleteById( orderId, productId)).withRel("deleteById") 
		);

		return ResponseEntity.ok(orderItemDto);
	}

	@GetMapping("/find")
	public ResponseEntity<OrderItemDto> findById(
			@RequestBody @NotNull(message = "Input must not be NULL") @Valid final OrderItemId orderItemId,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		log.info("*** OrderItemDto, resource; fetch orderItem by id *");
		return ResponseEntity.ok(this.orderItemService.findById(orderItemId, locale));
	}

	@PostMapping
	public ResponseEntity<OrderItemDto> save(
			@RequestBody @NotNull(message = "Input must not be NULL") @Valid final OrderItemDto orderItemDto) {
		log.info("*** OrderItemDto, resource; save orderItem *");
		return ResponseEntity.ok(this.orderItemService.save(orderItemDto));
	}

	@PutMapping
	public ResponseEntity<OrderItemDto> update(
			@RequestBody @NotNull(message = "Input must not be NULL") @Valid final OrderItemDto orderItemDto) {
		log.info("*** OrderItemDto, resource; update orderItem *");
		return ResponseEntity.ok(this.orderItemService.update(orderItemDto));
	}

	@DeleteMapping("/{orderId}/{productId}")
	public ResponseEntity<Boolean> deleteById(
			@PathVariable("orderId") final String orderId,
			@PathVariable("productId") final String productId) {
		log.info("*** Boolean, resource; delete orderItem by id *");
		this.orderItemService.deleteById(new OrderItemId(Integer.parseInt(orderId), Integer.parseInt(productId)));
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deleteById(
			@RequestBody @NotNull(message = "Input must not be NULL") @Valid final OrderItemId orderItemId) {
		log.info("*** Boolean, resource; delete orderItem by id *");
		this.orderItemService.deleteById(orderItemId);
		return ResponseEntity.ok(true);
	}

}
