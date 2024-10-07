package ru.otus.testingdemo.service;

import ru.otus.testingdemo.dto.WeatherMeasurementDto;

import java.util.List;

public interface WeatherMeasurementService {
    List<WeatherMeasurementDto> findAll();
    WeatherMeasurementDto save(WeatherMeasurementDto measurement);
    boolean existsById(long id);
    void deleteById(long id);
}
