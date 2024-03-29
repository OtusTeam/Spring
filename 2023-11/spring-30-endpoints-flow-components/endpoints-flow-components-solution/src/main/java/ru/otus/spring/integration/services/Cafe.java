package ru.otus.spring.integration.services;


import java.util.Collection;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

@MessagingGateway
public interface Cafe {

	@Gateway(requestChannel = "itemsChannel", replyChannel = "foodChannel")
	Collection<Food> process(Collection<OrderItem> orderItem);
}
