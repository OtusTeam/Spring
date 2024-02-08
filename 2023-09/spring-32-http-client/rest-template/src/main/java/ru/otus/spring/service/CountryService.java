package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.dto.Country;

public interface CountryService {

	Country findByCode(String id);

	List<Country> getAll();
}
