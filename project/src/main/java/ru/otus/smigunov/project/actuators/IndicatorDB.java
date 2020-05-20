package ru.otus.smigunov.project.actuators;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.smigunov.project.repositories.UserRepository;

@Component
@AllArgsConstructor
public class IndicatorDB implements HealthIndicator {

    private final UserRepository userRepository;

    @Override
    public Health health() {
        try {
            // Тестовое обращение к БД для определения факта наличия подключения
            userRepository.findById(-1L);
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().build();
        }
    }
}
