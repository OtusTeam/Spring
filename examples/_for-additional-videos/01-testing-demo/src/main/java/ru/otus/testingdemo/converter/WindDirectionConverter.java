package ru.otus.testingdemo.converter;


import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WindDirectionConverter {

    private final Map<Integer, String> directionMap = Map.of(
            0, "N",
            360, "N",
            90, "E",
            180, "S",
            270, "W",
            45, "NE",
            135, "SE",
            225, "SW",
            315, "NW"
    );

    public String convert(int windDegree){
        if (windDegree > 0 && windDegree < 90) {
            windDegree = 45;
        } else if (windDegree > 90 && windDegree < 180) {
            windDegree = 135;
        } else if (windDegree > 180 && windDegree < 270) {
            windDegree = 225;
        } else if (windDegree > 270 && windDegree < 360) {
            windDegree = 315;
        }
        return directionMap.get(windDegree);
    }
}
