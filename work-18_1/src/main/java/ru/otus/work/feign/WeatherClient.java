package ru.otus.work.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.work.feign.dto.WeatherResponse;

@FeignClient(name = "WeatherClient", url = "${feign.weather.url}", fallback = FallbackWeatherClient.class)
public interface WeatherClient {
    @GetMapping(value = "weather?lat={lat}&lon={lon}&appid={apikey}")
    WeatherResponse getWeather(@PathVariable String lat, @PathVariable String lon, @PathVariable String apikey);
}