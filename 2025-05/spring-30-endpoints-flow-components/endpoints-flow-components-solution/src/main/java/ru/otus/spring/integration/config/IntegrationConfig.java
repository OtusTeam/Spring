package ru.otus.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

import ru.otus.spring.integration.domain.Food;
import ru.otus.spring.integration.services.KitchenService;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> itemsChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> foodChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow cafeFlow(KitchenService kitchenService) {
        return IntegrationFlow.from(itemsChannel())
                .split()
                .handle(kitchenService, "cook")
                .<Food, Food>transform(f -> new Food(f.name().toUpperCase()))
                .aggregate()
                .channel(foodChannel())
                .get();
    }
}
