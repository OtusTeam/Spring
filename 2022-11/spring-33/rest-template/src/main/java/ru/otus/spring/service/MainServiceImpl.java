package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.dto.Country;

@Slf4j
@RequiredArgsConstructor
@Service
public class MainServiceImpl implements MainService {
	private final CountryServiceRest countryService;

	@Override
	public void start() {
		Country country = countryService.findByCode("col");
		log.info("Find {}", country);
//		country = countryService.findByCode("rus");
//		log.info("Find {}", country);
	}
}
