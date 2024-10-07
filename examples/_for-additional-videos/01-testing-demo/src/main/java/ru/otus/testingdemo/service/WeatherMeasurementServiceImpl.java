package ru.otus.testingdemo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.testingdemo.converter.WeatherMeasurement2DtoConverter;
import ru.otus.testingdemo.dto.WeatherMeasurementDto;
import ru.otus.testingdemo.model.WeatherMeasurement;
import ru.otus.testingdemo.repository.CityRepository;
import ru.otus.testingdemo.repository.WeatherMeasurementRepository;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class WeatherMeasurementServiceImpl implements WeatherMeasurementService {

    private final CityRepository cityRepository;
    private final WeatherMeasurementRepository measurementRepository;
    private final WeatherMeasurement2DtoConverter converter;

    @Override
    public List<WeatherMeasurementDto> findAll() {
        return measurementRepository.findAll().stream()
                .map(converter::convert).toList();
    }

    @Override
    public WeatherMeasurementDto save(WeatherMeasurementDto measurement) {
        var measurementToSave = converter.convert(measurement);
        setCityAndDateIfNecessary(measurementToSave, measurement.getCityLatinName(), measurement.getTime());
        var result = measurementRepository.save(measurementToSave);
        return converter.convert(result);
    }

    @Override
    public boolean existsById(long id) {
        return measurementRepository.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Measurement with given id not found");
        }
        measurementRepository.deleteById(id);
    }

    private void setCityAndDateIfNecessary(WeatherMeasurement measurementToSave, String cityLatinName,
                                           String measurementTime) {
        var city = cityRepository.findByLatinName(cityLatinName);
        measurementToSave.setCity(city);
        if (isNull(measurementTime)) {
            measurementToSave.setTime(new Date());
        }
    }
}
