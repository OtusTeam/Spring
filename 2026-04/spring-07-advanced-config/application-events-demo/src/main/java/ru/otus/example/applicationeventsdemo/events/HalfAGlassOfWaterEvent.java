package ru.otus.example.applicationeventsdemo.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class HalfAGlassOfWaterEvent extends ApplicationEvent {

    @Getter
    private final String payload;

    public HalfAGlassOfWaterEvent(Object source) {
        super(source);
        payload = "Осталось половина стакана воды!!!";
    }
}
