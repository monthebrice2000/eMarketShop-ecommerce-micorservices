package com.emarketshop.product.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.emarketshop.product.dto.ProductDto;
import com.emarketshop.product.dto.response.collection.DtoCollectionResponse;
import com.emarketshop.product.service.ProductService;
import com.emarketshop.product.utils.UserContextHolder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductResource {

	private final ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

	@GetMapping
	public ResponseEntity<DtoCollectionResponse<ProductDto>> findAll() throws TimeoutException {
		log.info("*** ProductDto List, controller; fetch all categories *");

		logger.debug("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

		return ResponseEntity.ok(new DtoCollectionResponse<>(this.productService.findAll()));
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> findById(
			@PathVariable("productId") @NotBlank(message = "Input must not be blank!") @Valid final String productId,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		log.info("*** ProductDto, resource; fetch product by id *");
		ProductDto productDto = this.productService.findById(Integer.parseInt(productId), locale);
		productDto.add(
				linkTo(methodOn(ProductResource.class).findById(productId, locale)).withSelfRel(),
				linkTo(methodOn(ProductResource.class).save(productDto)).withRel("save"),
				linkTo(methodOn(ProductResource.class).update(productDto)).withRel("update"),
				linkTo(methodOn(ProductResource.class).deleteById(String.format("%d", productDto.getProductId()),
						locale)).withRel("deleteById"));
		return ResponseEntity.ok(productDto);
	}

	@PostMapping
	public ResponseEntity<ProductDto> save(
			@RequestBody @NotNull(message = "Input must not be NULL!") @Valid final ProductDto productDto) {
		log.info("*** ProductDto, resource; save product *");
		return ResponseEntity.ok(this.productService.save(productDto));
	}

	@PutMapping
	public ResponseEntity<ProductDto> update(
			@RequestBody @NotNull(message = "Input must not be NULL!") @Valid final ProductDto productDto) {
		log.info("*** ProductDto, resource; update product *");
		return ResponseEntity.ok(this.productService.update(productDto));
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> update(
			@PathVariable("productId") @NotBlank(message = "Input must not be blank!") @Valid final String productId,
			@RequestBody @NotNull(message = "Input must not be NULL!") @Valid final ProductDto productDto,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		log.info("*** ProductDto, resource; update product with productId *");
		return ResponseEntity.ok(this.productService.update(Integer.parseInt(productId), productDto, locale));
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Boolean> deleteById(
			@PathVariable("productId") final String productId,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		log.info("*** Boolean, resource; delete product by id *");
		this.productService.deleteById(Integer.parseInt(productId), locale);
		return ResponseEntity.ok(true);
	}

}
