package ru.otus.spring.integration.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.integration.service.KitchenService;

@Configuration
public class IntegrationConfig {

    private static final int QUEUE_CAPACITY = 10;
    private static final String COOK_METHOD_NAME = "cook";

    @Bean
    public QueueChannel itemsChannel() {
        return MessageChannels.queue(QUEUE_CAPACITY).get();
    }

    @Bean
    public PublishSubscribeChannel foodChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow cafeFlow(KitchenService kitchenService) {
        return IntegrationFlows.from(itemsChannel())
                .split()
                .handle(kitchenService, COOK_METHOD_NAME)
                .aggregate()
                .channel(foodChannel())
                .get();
    }

}
