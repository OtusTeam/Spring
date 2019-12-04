package ru.otus.spring.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

import java.util.Collection;

// TODO: add messaging gateway annotation
@MessagingGateway
public interface Cafe {

    // TODO: add gateway annotation with required channels
    @Gateway(requestChannel = "itemsChannel", replyChannel = "foodChannel")
    Collection<Food> process( Collection<OrderItem> orderItem);
}
