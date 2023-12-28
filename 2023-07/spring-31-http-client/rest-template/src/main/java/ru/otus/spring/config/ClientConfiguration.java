package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class ClientConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			requestTemplate.query("access_key", "[!!!Your key!!!]");
		};
	}
}
