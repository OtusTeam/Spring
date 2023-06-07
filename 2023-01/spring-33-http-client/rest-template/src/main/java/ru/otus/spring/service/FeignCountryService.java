package ru.otus.spring.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.otus.spring.config.ClientConfiguration;
import ru.otus.spring.dto.Country;

@FeignClient(value = "countrylayer", url = "http://api.countrylayer.com/v2/", configuration = ClientConfiguration.class)
@Cacheable("countries")
@Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
public interface FeignCountryService {
	@RequestMapping(method = RequestMethod.GET, value = "/alpha/{code}", produces = "application/json")
	Country findByCode(@PathVariable("code") String code);

}
