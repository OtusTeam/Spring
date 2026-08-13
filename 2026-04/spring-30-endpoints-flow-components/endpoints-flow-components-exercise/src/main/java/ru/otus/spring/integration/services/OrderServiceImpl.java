package ru.otus.spring.integration.services;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	private static final String[] MENU = {"coffee", "tea", "smoothie", "whiskey", "beer", "cola", "water"};

	private final CafeGateway cafe;

	public OrderServiceImpl(CafeGateway cafe) {
		this.cafe = cafe;
	}

	@Override
	public void startGenerateOrdersLoop() {
		for (int i = 0; i < 10; i++) {
			OrderItem orderItem = generateOrderItem();
			int num = i + 1;
			log.info("{}, New orderItem: {}", num, orderItem.itemName());
			Food food = cafe.process(orderItem);
			log.info("{}, Ready food: {}", num, food.name());
			delay();
		}
	}

	private static OrderItem generateOrderItem() {
		return new OrderItem(MENU[RandomUtils.nextInt(0, MENU.length)]);
	}

	private void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
