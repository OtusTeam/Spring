package ru.otus.spring.test.polling;

import static java.lang.Thread.sleep;

import java.time.Duration;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.endpoint.PollingConsumer;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class PollingApp {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(PollingApp.class, args);
		log.warn("POLLER:");
		Map<String, PollingConsumer> pollers = ctx.getBeansOfType(PollingConsumer.class);
		int i = 0;
		for (Map.Entry<String, PollingConsumer> entry : pollers.entrySet()) {
			log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
		}
		log.warn("CHANNELS:");
		Map<String, MessageChannel> channels = ctx.getBeansOfType(MessageChannel.class);
		i = 0;
		for (Map.Entry<String, MessageChannel> entry : channels.entrySet()) {
			log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
		}
		log.warn("HANDLERS:");
		i = 0;
		Map<String, MessageHandler> endpoints = ctx.getBeansOfType(MessageHandler.class);
		for (Map.Entry<String, MessageHandler> entry : endpoints.entrySet()) {
			log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
		}

		Polling polling = ctx.getBean(Polling.class);
		String result = polling.send("test");
		log.warn("Polling result: {}", result);

		sleep(5000);
		ctx.close();
	}

	@MessagingGateway
	public interface Polling {
		@Gateway(requestChannel = "flow.input", replyChannel = "pubSub")
		String send(String value);
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {
//		return Pollers.fixedRate(10_000).get();
		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setMaxMessagesPerPoll(5);
		pollerMetadata.setTrigger(new PeriodicTrigger(Duration.ofSeconds(3)));
		return pollerMetadata;
	}

	@Bean
	public IntegrationFlow flow() {
		return f -> f
				.channel("p2p")
				.channel("pubSub");
	}

	@Bean
	public MessageChannelSpec<?, ?> p2p() {
		return MessageChannels.queue("p2p", 10);
	}

	@Bean
	public MessageChannelSpec<?, ?> p2p2() {
		return MessageChannels.priority("p2p2").capacity(10);
	}

	@Bean
	public MessageChannelSpec<?, ?> pubSub() {
		return MessageChannels.publishSubscribe("pubSub");
	}
}
