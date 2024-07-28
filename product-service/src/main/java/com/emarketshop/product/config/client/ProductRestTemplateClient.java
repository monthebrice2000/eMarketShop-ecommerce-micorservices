// package com.emarketshop.shipping_service.service.client;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;

// import com.emarketshop.shipping_service.domain.Product;
// import com.emarketshop.shipping_service.repository.ProductRedisRepository;
// import com.emarketshop.shipping_service.utils.UserContext;

// // import brave.ScopedSpan;
// // import brave.Tracer;

// @Component
// public class ProductRestTemplateClient {
// 	@Autowired
// 	RestTemplate restTemplate;

// 	// @Autowired
// 	// Tracer tracer;

// 	@Autowired
// 	ProductRedisRepository redisRepository;

// 	private static final Logger logger = LoggerFactory.getLogger(ProductRestTemplateClient.class);

// 	public Product getProduct(Integer productId){
// 		logger.debug("In Licensing Service.getProduct: {}", UserContext.getCorrelationId());

// 		Product Product = checkRedisCache(productId);

// 		if (Product != null){
// 			logger.debug("I have successfully retrieved an Product {} from the redis cache: {}", productId, Product);
// 			return Product;
// 		}

// 		logger.debug("Unable to locate Product from the redis cache: {}.", productId);

// 		ResponseEntity<Product> restExchange =
// 				restTemplate.exchange(
// 						"http://gateway:8072/Product/v1/Product/{productId}",
// 						HttpMethod.GET,
// 						null, Product.class, productId);

// 		/*Save the record from cache*/
// 		Product = restExchange.getBody();
// 		if (Product != null) {
// 			cacheProductObject(Product);
// 		}

// 		return restExchange.getBody();
// 	}

// 	private Product checkRedisCache(Integer productId) {
// 		// ScopedSpan newSpan = tracer.startScopedSpan("readLicensingDataFromRedis");
// 		try {
// 			return redisRepository.findById(productId).orElse(null);
// 		}catch (Exception ex){
// 			logger.error("Error encountered while trying to retrieve Product {} check Redis Cache.  Exception {}", productId, ex);
// 			return null;
// 		}finally {
// 			// newSpan.tag("peer.service", "redis");
// 			// newSpan.annotate("Client received");
// 			// newSpan.finish();
// 		}
// 	}

// 	private void cacheProductObject(Product Product) {
// 		try {
// 			redisRepository.save(Product);
// 		}catch (Exception ex){
// 			logger.error("Unable to cache Product {} in Redis. Exception {}", Product.getProductId(), ex);
// 		}
// 	}
// }
