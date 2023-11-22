package ru.otus.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import ru.otus.spring.dto.Country;

@Service
public class WebCountryService implements CountryService {

	private static final Logger log = LoggerFactory.getLogger(WebCountryService.class);

	private final WebClient webClient = WebClient.builder()
			.filter(errorHandler())
			.build();

	@Override
	public Country findByCode(String id) {
		log.info("Web client Request findByCode");
		return webClient.get()
				.uri("http://api.countrylayer.com/v2/alpha/" + id + "?access_key=[!!!Your key!!!]")
				.retrieve()
				.bodyToMono(Country.class)
				.block();
	}

	@Override
	public List<Country> getAll() {
		log.info("Web client Request findByCode");
		return webClient.get()
				.uri("http://api.countrylayer.com/v2/all?access_key=[!!!Your key!!!]")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Country>>() {
				})
				.block();
	}

	private static ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			if (clientResponse.statusCode().is5xxServerError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)));
			} else if (clientResponse.statusCode().is4xxClientError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)));
			} else {
				return Mono.just(clientResponse);
			}
		});
	}

}
