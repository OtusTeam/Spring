package ru.otus.testingdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.testingdemo.dto.WeatherProjection;
import ru.otus.testingdemo.repository.WeatherRepository;

@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public WeatherProjection findByCityName(String cityName) {
        return weatherRepository.findByCityName(cityName);
    }
}
