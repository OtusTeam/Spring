package ru.otus.testingdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherProjection {

    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;

    private double windSpeed;
    private int windDegree;
    private String windDirection;

    @JsonProperty("main")
    private void unpackNestedMain(Map<String, String> main) {
        this.temp = Double.parseDouble(main.get("temp"));
        this.feelsLike = Double.parseDouble(main.get("feels_like"));
        this.tempMin = Double.parseDouble(main.get("temp_min"));
        this.tempMax = Double.parseDouble(main.get("temp_max"));
        this.pressure = Integer.parseInt(main.get("pressure"));
        this.humidity = Integer.parseInt(main.get("humidity"));
    }

    @JsonProperty("wind")
    private void unpackNestedWind(Map<String, String> main) {
        this.windSpeed = Double.parseDouble(main.get("speed"));
        this.windDegree = Integer.parseInt(main.get("deg"));
    }

}
