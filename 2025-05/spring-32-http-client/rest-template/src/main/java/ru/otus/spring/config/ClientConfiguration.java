package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class ClientConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor(ClientProperties properties) {
		return requestTemplate -> requestTemplate.query("access_key", properties.getKey());
	}
}
