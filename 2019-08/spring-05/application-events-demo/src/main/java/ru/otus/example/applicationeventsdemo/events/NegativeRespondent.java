package ru.otus.example.applicationeventsdemo.events;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NegativeRespondent implements ApplicationListener<HalfAGlassOfWaterEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(HalfAGlassOfWaterEvent halfAGlassOfWaterEvent) {
        Thread.sleep(100);
        System.out.println("Негативно настроенный слушатель");
        System.out.println(String.format("- %s", halfAGlassOfWaterEvent.getPayload()));
        System.out.println("- Какой ужас. Теперь он наполовину пуст!!!\n\n");
    }
}
