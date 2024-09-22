package ru.otus.spring.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.DirectChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.PollableChannel;

@Configuration
public class IntegrationConfig {

	@Bean
	public PollableChannel queueChannel() {
		return new QueueChannel(100);
	}

	@Bean
	public DirectChannelSpec subscribableDirectChannel() {
		return MessageChannels.direct("subscribableDirectChannel");
	}

}
