package ru.otus.testingdemo.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.testingdemo.dto.WeatherProjection;

@FeignClient(name = "weatherClient", url = "${weather.service.baseUrl}")
public interface WeatherRepository {

    @GetMapping("/data/2.5/weather?appid=${weather.service.apiKey}&units=metric&q={cityName}")
    WeatherProjection findByCityName(@RequestParam("q") String cityName);
}
