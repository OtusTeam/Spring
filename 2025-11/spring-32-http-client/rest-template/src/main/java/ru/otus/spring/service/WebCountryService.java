package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.spring.config.ClientProperties;
import ru.otus.spring.dto.Country;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WebCountryService implements CountryService {
    final ClientProperties properties;

    final WebClient webClient;

    public WebCountryService(ClientProperties properties) {
        this.properties = properties;
        this.webClient = WebClient.builder()
                .baseUrl(properties.getUrl())
                .defaultUriVariables(Map.of("key", properties.getKey()))
                .filter(errorHandler())
                .build();
    }

    @Override
    public Country findByCode(String id) {
        log.info("Web client Request findByCode");
        return webClient.get()
                .uri("/alpha/{id}?access_key={key}", Map.of("id", id))
                .retrieve()
                .bodyToMono(Country.class)
                .block();
    }

    @Override
    public List<Country> getAll() {
        log.info("Web client Request findByCode");
        return webClient.get()
                .uri("/all?access_key={key}")
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
