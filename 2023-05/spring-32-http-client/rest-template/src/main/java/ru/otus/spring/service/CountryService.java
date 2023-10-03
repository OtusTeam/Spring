package ru.otus.spring.service;

import ru.otus.spring.dto.Country;

public interface CountryService {

	Country findByCode(String id);
}
