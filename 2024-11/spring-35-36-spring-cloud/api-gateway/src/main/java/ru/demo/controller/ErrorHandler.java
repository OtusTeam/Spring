package ru.demo.controller;

import java.net.ConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Order(-2)
@Component
public class ErrorHandler implements ErrorWebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public Mono<Void> handle(@NonNull ServerWebExchange serverWebExchange, @NonNull Throwable thr) {
        var bufferFactory = serverWebExchange.getResponse().bufferFactory();
        var response = serverWebExchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        if (thr instanceof ConnectException) {
            log.error("Target host connection error", thr);
            response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
            var dataBuffer = bufferFactory.wrap("Target host connection error".getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        } else {
            log.error("Unhandled exception", thr);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            var dataBuffer = bufferFactory.wrap("Unhandled error".getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }
    }
}
