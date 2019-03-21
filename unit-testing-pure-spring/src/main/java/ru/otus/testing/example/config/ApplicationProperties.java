package ru.otus.testing.example.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationProperties {
    private String initialData;
}
