package ru.otus.work.feign;

import org.springframework.stereotype.Component;
import ru.otus.work.feign.dto.MainDTO;
import ru.otus.work.feign.dto.WeatherDTO;
import ru.otus.work.feign.dto.WeatherResponse;

import java.util.Collections;

@Component
public class FallbackWeatherClient implements WeatherClient {
    @Override
    public WeatherResponse getWeather(String lat, String lon, String apikey) {

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setName("Fallback");
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setDescription("Fallback description");
        weatherResponse.setWeather(Collections.singletonList(weatherDTO));
        MainDTO mainDTO = new MainDTO();
        mainDTO.setTemp(10d);
        weatherResponse.setMain(mainDTO);
        return weatherResponse;
    }
}
