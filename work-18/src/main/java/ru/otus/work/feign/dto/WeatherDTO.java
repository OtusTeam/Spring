package ru.otus.work.feign.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDTO {
    private String main;
    private String description;
}
