package ru.otus.testingdemo.repository;

import ru.otus.testingdemo.model.City;

import java.util.List;

public interface CityRepository {
    List<City> findAll();
    City findByLatinName(String latinName);
}
