package com.emarketshop.payment_service.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emarketshop.payment_service.constant.AppConstant;
import com.emarketshop.payment_service.dto.OrderDto;
import com.emarketshop.payment_service.dto.PaymentDto;
import com.emarketshop.payment_service.exception.wrapper.PaymentNotFoundException;
import com.emarketshop.payment_service.helper.PaymentMappingHelper;
import com.emarketshop.payment_service.repository.PaymentRepository;
import com.emarketshop.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	MessageSource messages;

	private final PaymentRepository paymentRepository;
	private final RestTemplate restTemplate;

	@Override
	public List<PaymentDto> findAll() {
		log.info("*** PaymentDto List, service; fetch all payments *");
		return this.paymentRepository.findAll().stream().map(PaymentMappingHelper::map).map(p -> {
			p.setOrderDto(this.restTemplate.getForObject(
					AppConstant.DiscoveredDomainsApi.ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(),
					OrderDto.class));
			return p;
		}).distinct().collect(Collectors.toUnmodifiableList());
	}

	@Override
	public PaymentDto findById(final Integer paymentId, final Locale locale) {
		log.info("*** PaymentDto, service; fetch payment by id *");
		return this.paymentRepository.findById(paymentId).map(PaymentMappingHelper::map).map(p -> {
			p.setOrderDto(this.restTemplate.getForObject(
					AppConstant.DiscoveredDomainsApi.ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(),
					OrderDto.class));
			return p;
		}).orElseThrow(() -> new PaymentNotFoundException(
				String.format(messages.getMessage("payment.findbyid.error.message", null, locale), paymentId)));
	}

	@Override
	public PaymentDto save(final PaymentDto paymentDto) {
		log.info("*** PaymentDto, service; save payment *");
		return PaymentMappingHelper.map(this.paymentRepository.save(PaymentMappingHelper.map(paymentDto)));
	}

	@Override
	public PaymentDto update(final PaymentDto paymentDto) {
		log.info("*** PaymentDto, service; update payment *");
		return PaymentMappingHelper.map(this.paymentRepository.save(PaymentMappingHelper.map(paymentDto)));
	}

	@Override
	public void deleteById(final Integer paymentId) {
		log.info("*** Void, service; delete payment by id *");
		this.paymentRepository.deleteById(paymentId);
	}

}
