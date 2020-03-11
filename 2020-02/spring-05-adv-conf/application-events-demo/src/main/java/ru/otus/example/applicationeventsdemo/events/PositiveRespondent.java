package ru.otus.example.applicationeventsdemo.events;

import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PositiveRespondent  {

    @SneakyThrows
    @EventListener
    public void onApplicationEvent(HalfAGlassOfWaterEvent halfAGlassOfWaterEvent) {
        Thread.sleep(100);
        System.out.println("Позитивно настроенный слушатель");
        System.out.println(String.format("- %s", halfAGlassOfWaterEvent.getPayload()));
        System.out.println("- Ничего. Главное, что он наполовину полон!!!\n\n");
    }
}
