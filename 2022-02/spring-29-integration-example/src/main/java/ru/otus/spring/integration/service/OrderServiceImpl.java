package ru.otus.spring.integration.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.domain.OrderItem;
import ru.otus.spring.integration.integration.Cafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final int ORDERS_DELAY_MILLS = 7000;
    private static final String[] MENU = {"coffee", "tea", "smoothie", "whiskey", "beer", "cola", "water"};

    private final Cafe cafe;

    public OrderServiceImpl(Cafe cafe) {
        this.cafe = cafe;
    }

    @SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement", "BusyWait"})
    @Override
    public void startOrdersLoop() throws Exception {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            Thread.sleep(ORDERS_DELAY_MILLS);

            pool.execute(() -> {
                Collection<OrderItem> items = generateOrderItems();
                System.out.println("New orderItems: " +
                        items.stream().map(OrderItem::getItemName)
                                .collect(Collectors.joining(",")));
                Collection<Food> food = cafe.process(items);
                System.out.println("Ready food: " + food.stream()
                        .map(Food::getName)
                        .collect(Collectors.joining(",")));
            });
        }
    }


    private OrderItem generateOrderItem() {
        return new OrderItem(MENU[RandomUtils.nextInt(0, MENU.length)]);
    }

    private Collection<OrderItem> generateOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            items.add(generateOrderItem());
        }
        return items;
    }
}
