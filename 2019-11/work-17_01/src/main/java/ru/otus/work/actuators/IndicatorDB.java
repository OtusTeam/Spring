package ru.otus.work.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.work.repostory.BookRepository;

@Component
public class IndicatorDB implements HealthIndicator {

    private final BookRepository bookRepository;

    public IndicatorDB(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        try{
            bookRepository.findAll();
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().build();
        }

    }
}
