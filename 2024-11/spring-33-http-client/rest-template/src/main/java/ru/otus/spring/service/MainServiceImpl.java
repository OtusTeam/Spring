package ru.otus.spring.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.dto.Country;

@Slf4j
@RequiredArgsConstructor
@Service
public class MainServiceImpl implements CommandLineRunner {
	//	private final TemplateCountryService countryService;
//	private final WebCountryService countryService;
//	private final RestCountryService countryService;
	private final FeignCountryService countryService;

	@Override
	public void run(String... args) {
//		List<Country> countries = countryService.getAll();
//		log.info("Countries: {}", countries);
		Country country = countryService.findByCode("col");
		log.info("Find {}", country);
//		country = countryService.findByCode("col");
//		log.info("Find {}", country);
//		country = countryService.findByCode("rus");
//		log.info("Find {}", country);
	}
}
