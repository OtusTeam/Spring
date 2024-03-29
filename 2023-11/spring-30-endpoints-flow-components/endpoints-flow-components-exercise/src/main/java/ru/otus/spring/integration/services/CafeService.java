package ru.otus.spring.integration.services;


import java.util.List;
import java.util.Map;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ru.otus.spring.integration.domain.OrderItem;

@MessagingGateway
public interface CafeService {

	@Gateway(requestChannel = "itemsChannel", replyChannel = "foodChannel")
	List<Map<String, Object>> process(List<OrderItem> orderItem);
}
