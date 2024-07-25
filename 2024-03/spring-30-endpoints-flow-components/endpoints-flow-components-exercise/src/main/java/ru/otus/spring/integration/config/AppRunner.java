package ru.otus.spring.integration.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.integration.services.OrderService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
	final OrderService orderService;

	@Override
	public void run(String... args) {
		orderService.startGenerateOrdersLoop();
	}
}
