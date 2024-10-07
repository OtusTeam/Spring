package ru.otus.testingdemo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.testingdemo.dto.WeatherProjection;
import ru.otus.testingdemo.repository.WeatherRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
//@ExtendWith(DavidBlaineExtension.class)
class WeatherServiceImplExtendedMockUnitTest {

    @Mock
    private WeatherRepository repository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @DisplayName("Should find correct weather by city name")
    @Test
    void shouldFindCorrectWeatherByCityName() {
        var cityName = "Saratov";
        var expectedWeather = new WeatherProjection(22d, 31d, 0d, 50d, 1000,
                100, 3000, 180, "N");
        given(repository.findByCityName(cityName)).willReturn(expectedWeather);

        var actualWeather = weatherService.findByCityName(cityName);

        verify(repository, times(1)).findByCityName(cityName);
        assertThat(actualWeather).isEqualTo(expectedWeather);
    }
}