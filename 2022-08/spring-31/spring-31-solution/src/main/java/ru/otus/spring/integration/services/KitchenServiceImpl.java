package ru.otus.spring.integration.services;

import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Override
    public Food cook(OrderItem orderItem) {
        System.out.println("Cooking " + orderItem.getItemName());
        delay();
        System.out.println("Cooking " + orderItem.getItemName() + " done");
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
