package ru.otus.spring.microservice.rest;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsCounter {

    private final MeterRegistry registry;

    public MetricsCounter(MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping("/counter")
    public void counter() {
        registry.counter("counter.example").increment();
    }

    @GetMapping("/gauge")
    public void gauge() {
        registry.gauge("gauge.example", Math.random());
    }
}
