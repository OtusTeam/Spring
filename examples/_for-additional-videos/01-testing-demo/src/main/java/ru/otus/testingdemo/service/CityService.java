package ru.otus.testingdemo.service;

import ru.otus.testingdemo.model.City;

import java.util.List;

public interface CityService {
    List<City> findAll();
}
