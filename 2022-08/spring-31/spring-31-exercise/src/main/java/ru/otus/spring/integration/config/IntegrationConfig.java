package ru.otus.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class IntegrationConfig {
    @Bean
    public QueueChannel itemsChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel foodChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    // TODO: create default poller

    @Bean
    public IntegrationFlow cafeFlow() {
        return IntegrationFlows.from("itemsChannel")
                // TODO: cook OrderItem in the kitchen
                // TODO*: add splitter and aggregator
                // TODO: forward it to the publish subscriber channel
                .get();
    }
}
