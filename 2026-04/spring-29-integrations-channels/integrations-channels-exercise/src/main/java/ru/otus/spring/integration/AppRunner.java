package ru.otus.spring.integration;

import static java.util.Objects.isNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
	final PollableChannel queueChannel;
	final SubscribableChannel subscribableDirectChannel;

	@Override
	public void run(String... args) throws Exception {
		log.warn("INIT");
		for (int i = 0; i < 10; i++) {
			queueChannel.send(MessageBuilder.withPayload("Start " + i).build());
		}
		log.warn("INIT FINISH");
		Thread.sleep(5000);

		log.warn("START");

		subscribableDirectChannel.subscribe((msg) -> log.warn("Receive msg: {}", msg));

		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleWithFixedDelay(() -> {
			log.warn("I am here!!!");
			Message<?> receivedMessage = queueChannel.receive(5000);
			if (isNull(receivedMessage)) {
				return;
			}
			subscribableDirectChannel.send(receivedMessage);
		}, 100, 300, TimeUnit.MILLISECONDS);
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
}
