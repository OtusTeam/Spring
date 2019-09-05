package ru.otus.example.greetingmicroservice.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("names-microservice")
public interface NamesMicroServiceClient {
    @GetMapping("/names/random")
    String randomName();
}
