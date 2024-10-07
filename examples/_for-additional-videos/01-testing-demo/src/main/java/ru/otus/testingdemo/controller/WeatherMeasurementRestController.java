package ru.otus.testingdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.testingdemo.dto.WeatherMeasurementDto;
import ru.otus.testingdemo.service.WeatherMeasurementService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WeatherMeasurementRestController {

    private final WeatherMeasurementService weatherMeasurementService;

    @GetMapping("/api/measurement")
    public List<WeatherMeasurementDto> findAll() {
        return weatherMeasurementService.findAll();
    }

    @PostMapping("/api/measurement")
    public ResponseEntity<?> save(@RequestBody WeatherMeasurementDto measurement) {
        weatherMeasurementService.save(measurement);
        return ResponseEntity.status(201).build();
    }

}
