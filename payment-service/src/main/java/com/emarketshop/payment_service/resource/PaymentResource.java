package com.emarketshop.payment_service.resource;

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

import com.emarketshop.payment_service.dto.PaymentDto;
import com.emarketshop.payment_service.dto.response.collection.DtoCollectionResponse;
import com.emarketshop.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentResource {

	private final PaymentService paymentService;

	@GetMapping
	public ResponseEntity<DtoCollectionResponse<PaymentDto>> findAll() {
		log.info("*** PaymentDto List, controller; fetch all payments *");
		return ResponseEntity.ok(new DtoCollectionResponse<>(this.paymentService.findAll()));
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<PaymentDto> findById(
			@PathVariable("paymentId") @NotBlank(message = "Input must not be blank") @Valid final String paymentId,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		log.info("*** PaymentDto, resource; fetch payment by id *");

		PaymentDto paymentDto = this.paymentService.findById(Integer.parseInt(paymentId), locale);
		paymentDto.add(
				linkTo(methodOn(PaymentResource.class).findById(paymentId, locale)).withSelfRel(),
				linkTo(methodOn(PaymentResource.class).save(paymentDto)).withRel("save"),
				linkTo(methodOn(PaymentResource.class).update(paymentDto)).withRel("update"),
				linkTo(methodOn(PaymentResource.class).deleteById(String.format("%d", paymentId)))
						.withRel("deleteById"));

		return ResponseEntity.ok(paymentDto);
	}

	@PostMapping
	public ResponseEntity<PaymentDto> save(
			@RequestBody @NotNull(message = "Input must not be NULL") @Valid final PaymentDto paymentDto) {
		log.info("*** PaymentDto, resource; save payment *");
		return ResponseEntity.ok(this.paymentService.save(paymentDto));
	}

	@PutMapping
	public ResponseEntity<PaymentDto> update(
			@RequestBody @NotNull(message = "Input must not be NULL") @Valid final PaymentDto paymentDto) {
		log.info("*** PaymentDto, resource; update payment *");
		return ResponseEntity.ok(this.paymentService.update(paymentDto));
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("paymentId") final String paymentId) {
		log.info("*** Boolean, resource; delete payment by id *");
		this.paymentService.deleteById(Integer.parseInt(paymentId));
		return ResponseEntity.ok(true);
	}

}
