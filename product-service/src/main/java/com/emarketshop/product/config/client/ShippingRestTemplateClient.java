package com.emarketshop.product.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.emarketshop.product.constant.AppConstant;
import com.emarketshop.product.domain.Shipping;

@Component
public class ShippingRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    final String shippingServiceAPI = AppConstant.DiscoveredDomainsApi.SHIPPING_SERVICE_API_URL;

    public Shipping getShipping(String orderId, String productId) {
        ResponseEntity<Shipping> restExchange = restTemplate.exchange(
                String.format("%s/{orderIdId}/{productId}", shippingServiceAPI), 
                HttpMethod.GET, null, Shipping.class,
                orderId, productId);
        return restExchange.getBody();
    }

}
