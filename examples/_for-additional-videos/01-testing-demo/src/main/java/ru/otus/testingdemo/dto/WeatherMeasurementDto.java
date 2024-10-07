package ru.otus.testingdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMeasurementDto {
    private Long id;
    private String time;
    private String cityName;
    private String cityLatinName;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;

    private double windSpeed;
    private int windDegree;
    private String windDirection;

    public WeatherMeasurementDto clone() {
        return new WeatherMeasurementDto(id, time, cityName, cityLatinName, temp, feelsLike, tempMin, tempMax, pressure,
                humidity, windSpeed, windDegree, windDirection);
    }
}
