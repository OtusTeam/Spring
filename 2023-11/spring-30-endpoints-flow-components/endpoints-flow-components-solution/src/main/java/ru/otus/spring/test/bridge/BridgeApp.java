package ru.otus.spring.test.bridge;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class BridgeApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BridgeApp.class, args);
		Map<String, MessageChannel> channels = ctx.getBeansOfType(MessageChannel.class);
		log.warn("CHANNELS:");
		int i = 0;
		for (Map.Entry<String, MessageChannel> entry : channels.entrySet()) {
			log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
		}
		log.warn("HANDLERS:");
		i = 0;
		Map<String, MessageHandler> endpoints = ctx.getBeansOfType(MessageHandler.class);
		for (Map.Entry<String, MessageHandler> entry : endpoints.entrySet()) {
			log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
		}

		Bridge bean = ctx.getBean(Bridge.class);
		List<String> strings = List.of("TEST1", "end");
		Collection<String> result = bean.send(strings);
		log.warn("Bridge send: {}, receive: {}", strings, result);
	}


	@MessagingGateway
	public interface Bridge {
		@Gateway(requestChannel = "flow.input"/*, replyChannel = "p2pChannel"*/)
		Collection<String> send(Collection<String> strings);
	}

	@Bean
	public IntegrationFlow flow() {
		return f -> f
				.channel(MessageChannels.queue("p2pChannel", 10));
	}
}
