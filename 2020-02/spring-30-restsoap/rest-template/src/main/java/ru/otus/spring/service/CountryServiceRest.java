package ru.otus.spring.service;

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

import java.net.URI;
import java.util.List;

@Service
public class CountryServiceRest implements CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceRest.class);

    private RestOperations rest = new RestTemplate();

    public Country getCountry(String id) {
        log.info("Request");
        return rest.getForObject("https://restcountries.eu/rest/v2/alpha/" + id, Country.class);
    }

    @Override
    public List<Country> getCountries(){
        RequestEntity request = new RequestEntity( HttpMethod.GET
                , UriComponentsBuilder.fromHttpUrl( "https://restcountries.eu" )
                    .path( "rest/v2" )
                    .queryParam( "locale", "ru" )
                    .build().toUri());

        log.info( request.getUrl().toString() );

        ResponseEntity<List<Country>> response =
                rest.exchange( request,
                new ParameterizedTypeReference<List<Country>>(){} );

        return response.getBody();
    }
}
