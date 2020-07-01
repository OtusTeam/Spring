package ru.otus.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.dto.Country;

@Service
public class CountryServiceRest implements CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceRest.class);

    private RestOperations rest = new RestTemplate();

    public Country getCountry(String id) {
        log.info("Request");
        return rest.getForObject("https://restcountries.eu/rest/v2/alpha/" + id, Country.class);
    }
}
