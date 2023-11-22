package ru.otus.spring.service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ru.otus.spring.dto.Country;

@Service
public class RestCountryService implements CountryService {

	private static final Logger log = LoggerFactory.getLogger(RestCountryService.class);

	private final RestOperations rest = new RestTemplate();

	@Override
	public Country findByCode(String id) {
		log.info("Rest Request");
		return rest.getForObject("http://api.countrylayer.com/v2/alpha/" + id + "?access_key=[!!!Your key!!!]",
				Country.class);
	}

	@Override
	public List<Country> getAll() {
		URI uri = UriComponentsBuilder.fromHttpUrl("http://api.countrylayer.com")
				.path("/v2/all")
				.queryParam("access_key", "[!!!Your key!!!]")
				.build().toUri();
		RequestEntity<List<Country>> requestEntity = new RequestEntity<>(HttpMethod.GET,
				uri);
//		List<Country> countries = rest.getForObject(uri, List.class);
		ResponseEntity<List<Country>> exchange = rest.exchange(requestEntity, new ParameterizedTypeReference<>() {
		});
		return exchange.getBody();
	}
}
