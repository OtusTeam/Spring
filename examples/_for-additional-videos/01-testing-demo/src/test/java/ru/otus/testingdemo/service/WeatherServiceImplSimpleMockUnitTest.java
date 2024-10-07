package ru.otus.testingdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.testingdemo.dto.WeatherProjection;
import ru.otus.testingdemo.repository.WeatherRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WeatherServiceImplSimpleMockUnitTest {

    private WeatherRepository repository;
    private WeatherServiceImpl weatherService;

    @BeforeEach
    void setUp() {
        repository = mock(WeatherRepository.class);
        weatherService = new WeatherServiceImpl(repository);
    }

    @DisplayName("Should find correct weather by city name")
    @Test
    void shouldFindCorrectWeatherByCityName() {
        var cityName = "Saratov";
        var expectedWeather = new WeatherProjection(22d, 31d, 0d, 50d, 1000,
                100, 3000, 180, "N");
        //MockHandlerImpl
        given(repository.findByCityName(cityName)).willReturn(expectedWeather);

        var actualWeather = weatherService.findByCityName(cityName);

        verify(repository, times(1)).findByCityName(cityName);
        assertThat(actualWeather).isEqualTo(expectedWeather);
    }
}