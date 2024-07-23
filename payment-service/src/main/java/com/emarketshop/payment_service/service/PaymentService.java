package com.emarketshop.payment_service.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.payment_service.dto.PaymentDto;

public interface PaymentService {
	
	List<PaymentDto> findAll();
	// PaymentDto findById(final Integer paymentId);
	PaymentDto findById(final Integer paymentId, final Locale locale);
	PaymentDto save(final PaymentDto paymentDto);
	PaymentDto update(final PaymentDto paymentDto);
	void deleteById(final Integer paymentId);
	
}
