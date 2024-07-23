package com.emarketshop.payment_service.helper;

import com.emarketshop.payment_service.domain.Payment;
import com.emarketshop.payment_service.dto.OrderDto;
import com.emarketshop.payment_service.dto.PaymentDto;

public interface PaymentMappingHelper {
	
	public static PaymentDto map(final Payment payment) {
		return PaymentDto.builder()
				.paymentId(payment.getPaymentId())
				.isPayed(payment.getIsPayed())
				.paymentStatus(payment.getPaymentStatus())
				.orderDto(
						OrderDto.builder()
							.orderId(payment.getOrderId())
							.build())
				.build();
	}
	
	public static Payment map(final PaymentDto paymentDto) {
		return Payment.builder()
				.paymentId(paymentDto.getPaymentId())
				.orderId(paymentDto.getOrderDto().getOrderId())
				.isPayed(paymentDto.getIsPayed())
				.paymentStatus(paymentDto.getPaymentStatus())
				.build();
	}
	
	
	
}










