package ru.otus.example.applicationeventsdemo.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HalfAGlassOfWaterEventPublisher implements EventsPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish() {
        publisher.publishEvent(new HalfAGlassOfWaterEvent(this));
    }
}
