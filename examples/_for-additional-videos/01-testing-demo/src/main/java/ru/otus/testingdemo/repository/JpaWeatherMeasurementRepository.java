package ru.otus.testingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testingdemo.model.WeatherMeasurement;

public interface JpaWeatherMeasurementRepository extends WeatherMeasurementRepository,
        JpaRepository<WeatherMeasurement, Long> {
}
