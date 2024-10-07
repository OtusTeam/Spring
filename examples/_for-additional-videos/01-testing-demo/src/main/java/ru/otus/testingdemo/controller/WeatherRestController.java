package ru.otus.testingdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.testingdemo.dto.WeatherProjection;
import ru.otus.testingdemo.service.WeatherService;

@RequiredArgsConstructor
@RestController
public class WeatherRestController {

    private final WeatherService weatherService;

    @GetMapping("/api/weather/{city}")
    public WeatherProjection getWeatherByCityName(@PathVariable("city") String cityName) {
        return weatherService.findByCityName(cityName);
    }
}
