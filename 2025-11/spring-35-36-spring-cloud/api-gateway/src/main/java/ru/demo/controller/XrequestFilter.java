package ru.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class XrequestFilter implements GatewayFilter {
    private static final String HEADER_X_REQUEST_ID = "X-Request-Id";
    private static final Logger log = LoggerFactory.getLogger(XrequestFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var guid = java.util.UUID.randomUUID().toString();
        log.info("requestId:{}", guid);

        var request =
                exchange.getRequest().mutate().header(HEADER_X_REQUEST_ID, guid).build();

        return chain.filter(exchange.mutate().request(request).build());
    }
}
