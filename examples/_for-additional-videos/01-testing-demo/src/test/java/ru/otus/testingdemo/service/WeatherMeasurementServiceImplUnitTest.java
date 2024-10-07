package ru.otus.testingdemo.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import ru.otus.testingdemo.converter.WeatherMeasurement2DtoConverter;
import ru.otus.testingdemo.dto.WeatherMeasurementDto;
import ru.otus.testingdemo.model.City;
import ru.otus.testingdemo.model.Weather;
import ru.otus.testingdemo.model.WeatherMeasurement;
import ru.otus.testingdemo.repository.CityRepository;
import ru.otus.testingdemo.repository.WeatherMeasurementRepository;

import java.util.Date;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WeatherMeasurementServiceImplUnitTest {

    private CityRepository cityRepository;
    private WeatherMeasurementRepository measurementRepository;
    private WeatherMeasurement2DtoConverter converter;
    private InOrder inOrder;
    private WeatherMeasurementServiceImpl weatherMeasurementService;

    @BeforeEach
    void setUp() {
        cityRepository = mock(CityRepository.class);
        measurementRepository = mock(WeatherMeasurementRepository.class);
        converter = mock(WeatherMeasurement2DtoConverter.class);
        inOrder = inOrder(cityRepository, measurementRepository, converter);
        weatherMeasurementService = new WeatherMeasurementServiceImpl(cityRepository, measurementRepository, converter);
    }

    @DisplayName("Should throw expected exception when deleted measurement does not exists")
    @Test
    void shouldThrowExpectedExceptionWhenDeletedMeasurementDoesNotExists() {
        given(measurementRepository.existsById(anyLong()))
                .willReturn(false)
                .willReturn(true);
        assertThatCode(() -> weatherMeasurementService.deleteById(1L)).isInstanceOf(EntityNotFoundException.class);
        assertThatCode(() -> weatherMeasurementService.deleteById(1L)).doesNotThrowAnyException();
    }

    @DisplayName("Should exec dependencies methods in correct way during save")
    @Test
    void shouldExecDependenciesMethodsInCorrectWayDuringSave() {
        var city = new City(1L, "Саратов", "Saratov");
        var weather = new Weather(22, 30, 12, 40, 1001, 47,
                30, 320, "N");
        var expectedMeasurement = new WeatherMeasurement(1L, new Date(), city, weather);

        var measurementDto = new WeatherMeasurementDto(expectedMeasurement.getId(), expectedMeasurement.getTime().toString(),
                city.getName(), city.getLatinName(), weather.getTemp(), weather.getFeelsLike(), weather.getTempMin(),
                weather.getTempMax(), weather.getPressure(), weather.getHumidity(), weather.getWindSpeed(),
                weather.getWindDegree(), weather.getWindDirection());

        given(converter.convert(measurementDto)).willReturn(expectedMeasurement);
        given(measurementRepository.save(expectedMeasurement)).willReturn(expectedMeasurement);
        given(converter.convert(expectedMeasurement)).willReturn(measurementDto);

        var savedMeasurementDto = weatherMeasurementService.save(measurementDto);

        assertThat(savedMeasurementDto).isEqualTo(measurementDto);

        // Переставить save наверх внутри
        verify(converter, times(1)).convert(measurementDto);
        verify(cityRepository, times(1)).findByLatinName(city.getLatinName());
        verify(measurementRepository, times(1)).save(expectedMeasurement);
        verify(converter, times(1)).convert(expectedMeasurement);
    }

    @DisplayName("Should convert expected measurements during find all")
    @Test
    void shouldConvertExpectedMeasurementsDuringFindAll() {
        var expectedMeasurements = LongStream.range(1, 101).boxed()
                .map(id -> new WeatherMeasurement(id, new Date(), null, null))
                .toList();

        given(measurementRepository.findAll()).willReturn(expectedMeasurements);
        var captor = ArgumentCaptor.forClass(WeatherMeasurement.class);

        weatherMeasurementService.findAll();

        verify(converter, times(100)).convert(captor.capture());
        var actualMeasurements = captor.getAllValues();
        assertThat(actualMeasurements).containsExactlyInAnyOrderElementsOf(expectedMeasurements);
    }
}