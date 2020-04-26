package ru.otus.work.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.work.feign.WeatherClient;
import ru.otus.work.feign.dto.WeatherResponse;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;
    private final LocalizedMessage localizedMessage;

    @Override
    public String getWeatherAsString() {
        WeatherResponse weatherResponse = weatherClient.getWeather("35", "139", "439d4b804bc8187953eb36d2a8c26a02");
        return localizedMessage.getMessage(
                "weatherString",
                new Object[]{weatherResponse.getName(), weatherResponse.getWeather().get(0).getDescription(), weatherResponse.getMain().getTemp()}
        );
    }
}
