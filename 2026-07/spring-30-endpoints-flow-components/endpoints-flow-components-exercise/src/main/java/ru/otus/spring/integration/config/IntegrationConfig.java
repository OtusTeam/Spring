package ru.otus.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;

@SuppressWarnings("unused")
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

	// TODO: create default poller

	@Bean
	public IntegrationFlow cafeFlow() {
		return IntegrationFlow.from(itemsChannel())
				// TODO: cook OrderItem in the kitchen
				// TODO*: add splitter and aggregator
				// TODO: forward it to the publish subscriber channel
				.get();
	}
}
