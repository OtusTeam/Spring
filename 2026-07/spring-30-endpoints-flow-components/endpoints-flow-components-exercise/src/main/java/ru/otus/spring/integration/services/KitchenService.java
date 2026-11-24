package ru.otus.spring.integration.services;

import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

public interface KitchenService {

	Food cook(OrderItem orderItem);
}
