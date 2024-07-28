package com.emarketshop.shipping_service;

import java.util.Locale;

import org.apache.tomcat.util.net.WriteBuffer.Sink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
// import org.springframework.cloud.stream.annotation.EnableBinding;
// import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// import com.emarketshop.shipping_service.config.ServiceConfig;

@SpringBootApplication
// @EnableDiscoveryClient
@RefreshScope
// @EnableBinding(Sink.class)
public class ShippingServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ShippingServiceApplication.class);

	// @Autowired
	// private ServiceConfig serviceConfig;

	public static void main(String[] args) {
		SpringApplication.run(ShippingServiceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		return messageSource;
	}

	// @StreamListener()
	// public void loggerSink(OrganizationChangeModel orgChange) {
	// logger.debug("Received an {} event for organization id {}",
	// orgChange.getAction(), orgChange.getOrganizationId());
	// }

	// @Bean
	// JedisConnectionFactory jedisConnectionFactory() {
	// 	String hostname = serviceConfig.getRedisServer();
	// 	int port = Integer.parseInt(serviceConfig.getRedisPort());
	// 	RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
	// 	return new JedisConnectionFactory(redisStandaloneConfiguration);
	// }

	// @Bean
	// public RedisTemplate<String, Object> redisTemplate() {
	// 	RedisTemplate<String, Object> template = new RedisTemplate<>();
	// 	template.setConnectionFactory(jedisConnectionFactory());
	// 	return template;
	// }

}
