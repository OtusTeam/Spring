package ru.otus.spring.integration.services;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String[] MENU = {"coffee", "tea", "smoothie", "whiskey", "beer", "cola", "water"};

    private final CafeGateway cafe;

    public OrderServiceImpl(CafeGateway cafe) {
        this.cafe = cafe;
    }

    public void startGenerateOrdersLoop() {
        for (int i = 0; i < 10; i++) {
            delay();

            OrderItem orderItem = generateOrderItem();
            System.out.println("New orderItem: " + orderItem.getItemName());
            Food food = cafe.process(orderItem);
            System.out.println("Ready food: " + food.getName());
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
