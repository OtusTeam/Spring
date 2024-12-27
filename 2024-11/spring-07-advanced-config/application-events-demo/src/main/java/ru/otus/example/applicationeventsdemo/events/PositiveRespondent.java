package ru.otus.example.applicationeventsdemo.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PositiveRespondent extends AbstractRespondent  {

    @EventListener
    public void onApplicationEvent(HalfAGlassOfWaterEvent halfAGlassOfWaterEvent) {
        delay(100);
        System.out.println("Позитивно настроенный слушатель");
        System.out.println(String.format("- %s", halfAGlassOfWaterEvent.getPayload()));
        System.out.println("- Ничего. Главное, что он наполовину полон!!!\n\n");
    }
}
