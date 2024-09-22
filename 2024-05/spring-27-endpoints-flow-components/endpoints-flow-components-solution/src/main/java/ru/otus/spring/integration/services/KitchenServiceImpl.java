package ru.otus.spring.integration.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

@Service
@Slf4j
public class KitchenServiceImpl implements KitchenService {

	@Override
	public Food cook(OrderItem orderItem) {
		log.info("Cooking {}", orderItem.getItemName());
		delay();
		log.info("Cooking {} done", orderItem.getItemName());
		return new Food(orderItem.getItemName());
	}

	private static void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
