package ru.otus.testingdemo.repository;

import ru.otus.testingdemo.model.WeatherMeasurement;

import java.util.List;

public interface WeatherMeasurementRepository {
    List<WeatherMeasurement> findAll();
    WeatherMeasurement save(WeatherMeasurement measurement);
    boolean existsById(long id);
    void deleteById(long id);
}
