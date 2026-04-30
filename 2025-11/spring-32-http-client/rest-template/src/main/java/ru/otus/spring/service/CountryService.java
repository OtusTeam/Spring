package ru.otus.spring.service;

import ru.otus.spring.dto.Country;

import java.util.List;

public interface CountryService {

    Country findByCode(String id);

    List<Country> getAll();
}
