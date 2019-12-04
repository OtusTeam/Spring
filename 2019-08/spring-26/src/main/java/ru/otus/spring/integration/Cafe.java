package ru.otus.spring.integration;


import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

import java.util.Collection;

// TODO: add messaging gateway annotation
public interface Cafe {

    // TODO: add gateway annotation with required channels
    Collection<Food> process( Collection<OrderItem> orderItem );
}
