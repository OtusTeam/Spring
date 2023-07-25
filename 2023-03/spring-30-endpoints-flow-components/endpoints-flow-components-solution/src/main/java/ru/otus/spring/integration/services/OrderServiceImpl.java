package ru.otus.spring.integration.services;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

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
		ForkJoinPool pool = ForkJoinPool.commonPool();
		for (int i = 0; i < 10; i++) {
			delay();
			int num = i + 1;
			pool.execute(() -> {
				Collection<OrderItem> items = generateOrderItems();
				log.info("{}, New orderItems: {}", num,
						items.stream().map(OrderItem::getItemName)
								.collect(Collectors.joining(",")));
				Collection<Food> food = cafe.process(items);
				log.info("{}, Ready food: {}", num, food.stream()
						.map(Food::getName)
						.collect(Collectors.joining(",")));
			});
		}
	}

	private static OrderItem generateOrderItem() {
		return new OrderItem(MENU[RandomUtils.nextInt(0, MENU.length)]);
	}

	private static Collection<OrderItem> generateOrderItems() {
		List<OrderItem> items = new ArrayList<>();
		for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
			items.add(generateOrderItem());
		}
		return items;
	}

	private void delay() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
