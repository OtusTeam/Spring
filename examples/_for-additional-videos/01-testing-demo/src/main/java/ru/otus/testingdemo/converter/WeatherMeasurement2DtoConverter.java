package ru.otus.testingdemo.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.testingdemo.dto.WeatherMeasurementDto;
import ru.otus.testingdemo.model.WeatherMeasurement;

@Mapper(componentModel = "spring")
public interface WeatherMeasurement2DtoConverter {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "time", source = "time", dateFormat = "dd.MM.yyyy HH:mm:ss")
    @Mapping(target = "cityName", source = "city.name")
    @Mapping(target = "cityLatinName", source = "city.latinName")
    @Mapping(target = "temp", source = "weather.temp")
    @Mapping(target = "feelsLike", source = "weather.feelsLike")
    @Mapping(target = "tempMin", source = "weather.tempMin")
    @Mapping(target = "tempMax", source = "weather.tempMax")
    @Mapping(target = "pressure", source = "weather.pressure")
    @Mapping(target = "humidity", source = "weather.humidity")
    @Mapping(target = "windSpeed", source = "weather.windSpeed")
    @Mapping(target = "windDegree", source = "weather.windDegree")
    @Mapping(target = "windDirection", source = "weather.windDirection")
    WeatherMeasurementDto convert(WeatherMeasurement measurement);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "time", source = "time", dateFormat = "dd.MM.yyyy HH:mm:ss")
    @Mapping(target = "city.name", source = "cityName")
    @Mapping(target = "city.latinName", source = "cityLatinName")
    @Mapping(target = "weather.temp", source = "temp")
    @Mapping(target = "weather.feelsLike", source = "feelsLike")
    @Mapping(target = "weather.tempMin", source = "tempMin")
    @Mapping(target = "weather.tempMax", source = "tempMax")
    @Mapping(target = "weather.pressure", source = "pressure")
    @Mapping(target = "weather.humidity", source = "humidity")
    @Mapping(target = "weather.windSpeed", source = "windSpeed")
    @Mapping(target = "weather.windDegree", source = "windDegree")
    @Mapping(target = "weather.windDirection", source = "windDirection")
    WeatherMeasurement convert(WeatherMeasurementDto measurement);
}