package ru.otus.spring.microservice.rest;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.binder.BaseUnits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class MetricsCounter {

    private final MeterRegistry registry;
    private final List<Object> objects = new ArrayList<>();
    // Часто сохранять ссылку на созданный Meter удобнее
    private final DistributionSummary distribution;
    private final Timer timer;

    public MetricsCounter(MeterRegistry registry) {
        this.registry = registry;

        // Создание Counter - монотонно возрастающий счётчик
        // Counter - можно менять вручную
        // В принципе, Counter можно и не регистрировать заранее
        Counter.builder("counter.example")
            .baseUnit(BaseUnits.EVENTS)
            .description("The number of method calls")
            .register(registry);

        // Gauge - просто одномерное значение
        // Изменяется, только, когда мы его наблюдаем
        // Обычно это - какая-то функция от состояния объекта
        Gauge.builder("gauge.example", objects, Collection::size)
            .baseUnit(BaseUnits.OBJECTS)
            .description("The number of events from the array")
            .register(registry);

        // Distribution - распределение какой-то величины
        // Можно ещё добавить персентиль(и)
        distribution = DistributionSummary.builder("distribution.example")
            .baseUnit("milliliters")
            .description("Milliliters of the teacher tears")
            // По умолчанию - только одно значение
            .distributionStatisticBufferLength(1000)
            .register(registry);

        // Timer - популярный вариант DistributionSummary
        // В нём есть удобные методы для работы
        // А также можно добавить SLA и гистограмму
        timer = Timer.builder("timer.example")
            .description("Time of the method execution")
            .publishPercentiles(0.50, 0.90, 0.95, 1.00)
            .publishPercentileHistogram()
            .register(registry);
    }

    @PostMapping("/counter")
    public void counter() {
        // Увеличивает counter
        registry.counter("counter.example").increment();
    }

    @PostMapping("/gauge")
    public void gauge() {
        // Косвенно увеличиваем gauge
        objects.add(new Object());
    }

    @PostMapping("/distribution")
    public void distribution() {
        distribution.record(Math.random());
    }

    @PostMapping("/timer")
    public void timer() throws InterruptedException {
        // Старт таймера
        Timer.Sample sample = Timer.start();

        // Делаем какие-то долгие действия
        Thread.sleep((long) (Math.random() * 500));

        // Окончание замера и запись результата
        // Да, это наносекундное разрешение
        sample.stop(timer);
    }

    // Да, эта аннотация не просто создаст таймер
    // Но и будет засекать время специальным аспектом
    // longTask - это LongTaskTimer, таймер с миллисекундным разрешением
    @Timed(
        value = "long.timer.example",
        longTask = true,
        description = "Annotated timer example"
    )
    @PostMapping("/long-timer")
    public void longTimer() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 5000));
    }
}
