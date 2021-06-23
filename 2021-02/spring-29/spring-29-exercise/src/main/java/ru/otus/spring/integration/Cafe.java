package ru.otus.spring.integration;


import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

// TODO: add messaging gateway annotation
public interface Cafe {

    // TODO: add gateway annotation with required channels
    Food process(OrderItem orderItem);
}
