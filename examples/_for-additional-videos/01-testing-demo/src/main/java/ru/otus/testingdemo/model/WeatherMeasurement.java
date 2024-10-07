package ru.otus.testingdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherMeasurement {
    private long id;
    private Date time;
    private City city;
    private Weather weather;

    public WeatherMeasurement clone() {
        return new WeatherMeasurement(id, time, new City(city.getId(), city.getName(), city.getLatinName()),
                new Weather(weather.getTemp(), weather.getFeelsLike(), weather.getTempMin(), weather.getTempMax(),
                        weather.getPressure(), weather.getHumidity(), weather.getWindSpeed(), weather.getWindDegree(),
                        weather.getWindDirection()));
    }
}
