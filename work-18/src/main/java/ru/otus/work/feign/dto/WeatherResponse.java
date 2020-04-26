package ru.otus.work.feign.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private String name;
    private List<WeatherDTO> weather;
    private MainDTO main;
}
