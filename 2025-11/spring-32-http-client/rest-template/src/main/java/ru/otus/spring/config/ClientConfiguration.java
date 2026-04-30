package ru.otus.spring.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class ClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(ClientProperties properties) {
        return requestTemplate -> requestTemplate.query("access_key", properties.getKey());
    }
}
