package ru.otus.work.actuators;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class IndexCounter {

    private final Counter counter;

    public IndexCounter(MeterRegistry registry) {
        counter = Counter
                .builder("indexcounter")
                .description("Index counter")
                .register(registry);
    }

    public void countedCall() {
        counter.increment();
    }
}