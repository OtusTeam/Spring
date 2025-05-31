package ru.otus.spring.integration;

import static java.util.Objects.isNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.DirectChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class App {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

		PollableChannel queueChannel = ctx.getBean("queueChannel", PollableChannel.class);
		SubscribableChannel subscribableDirectChannel = ctx.getBean("subscribableDirectChannel", SubscribableChannel.class);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleWithFixedDelay(() -> {
			log.warn("I am here!!!");
			Message<?> receivedMessage = queueChannel.receive(5000);
			if (isNull(receivedMessage)) {
				return;
			}
			subscribableDirectChannel.send(receivedMessage);
		}, 100, 300, TimeUnit.MILLISECONDS);
		subscribableDirectChannel.subscribe((msg) -> log.warn("Receive msg: {}", msg));


		log.warn("INIT");
		for (int i = 0; i < 10; i++) {
			queueChannel.send(MessageBuilder.withPayload("Start " + i).build());
		}
		log.warn("INIT FINISH");
		Thread.sleep(5000);

		log.warn("START");

		log.warn("START FINISH");

		log.warn("");
		queueChannel.send(MessageBuilder.withPayload("Hello").build());
		log.warn("");
		queueChannel.send(MessageBuilder.withPayload("Hello2").build());

		Thread.sleep(2_000);

		log.warn("");
		queueChannel.send(MessageBuilder.withPayload("Hello3").build());

		Thread.sleep(3_000);
		executor.shutdown();
	}

	@Bean
	public PollableChannel queueChannel() {
		return new QueueChannel(9);
	}

	@Bean
	public DirectChannelSpec subscribableDirectChannel() {
		return MessageChannels.direct("subscribableDirectChannel");
	}
}