package ru.otus.testingdemo.service;

import ru.otus.testingdemo.dto.WeatherProjection;

public interface WeatherService {
    WeatherProjection findByCityName(String cityName);
}
