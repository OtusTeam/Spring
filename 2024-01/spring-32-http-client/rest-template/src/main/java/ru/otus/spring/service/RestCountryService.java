package ru.otus.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.config.ClientProperties;
import ru.otus.spring.dto.Country;

@Slf4j
@Service
public class RestCountryService implements CountryService {
	final ClientProperties properties;

	final RestClient restClient;

	public RestCountryService(ClientProperties properties) {
		this.properties = properties;
		this.restClient = RestClient.builder()
				.requestFactory(new HttpComponentsClientHttpRequestFactory())
//			.messageConverters(converters -> converters.add(new MyCustomMessageConverter()))
				.baseUrl(properties.getUrl())
				.defaultUriVariables(Map.of("key", properties.getKey()))
//			.defaultHeader("Header", "")
//			.requestInterceptor(myCustomInterceptor)
//			.requestInitializer(myCustomInitializer)
				.build();
	}

	@Override
	public Country findByCode(String id) {
		log.info("Rest client Request findByCode");
		return restClient.get()
				.uri("/alpha/{id}?access_key={key}", Map.of("id", id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(Country.class);
	}

	@Override
	public List<Country> getAll() {
		log.info("Web client Request findByCode");
		return restClient.get()
				.uri("/all?access_key={key}")
				.retrieve()
				.body(new ParameterizedTypeReference<>() {
				});
	}
}
