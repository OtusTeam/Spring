package ru.otus.example.applicationeventsdemo.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PositiveRespondent implements ApplicationListener<HalfAGlassOfWaterEvent> {

    @Override
    public void onApplicationEvent(HalfAGlassOfWaterEvent halfAGlassOfWaterEvent) {
        System.out.println("Позитивно настроенный слушатель");
        System.out.println(String.format("- %s", halfAGlassOfWaterEvent.getPayload()));
        System.out.println("- Ничего. Главное, что он наполовину полон!!!\n\n");
    }
}
