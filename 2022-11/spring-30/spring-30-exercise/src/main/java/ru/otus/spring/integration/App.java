package ru.otus.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.support.MessageBuilder;
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
		SubscribableChannel directChannel = ctx.getBean("directChannel", SubscribableChannel.class);


		log.warn("INIT");
		for (int i = 0; i < 10; i++) {
			queueChannel.send(MessageBuilder.withPayload("Start " + i).build());
		}
		log.warn("INIT FINISH");
		Thread.sleep(5000);

		log.warn("START");
		directChannel.subscribe((msg) -> log.warn("Receive msg: {}", msg));
		new Thread(() -> {
			while (true) {
				directChannel.send(queueChannel.receive());
			}
		}).start();
		log.warn("START FINISH");
		Thread.sleep(5000);

		log.warn("");
		queueChannel.send(MessageBuilder.withPayload("Hello").build());
		Thread.sleep(5000);

		log.warn("");
		queueChannel.send(MessageBuilder.withPayload("Hello2").build());
		Thread.sleep(5000);

		log.warn("");
		queueChannel.send(MessageBuilder.withPayload("Hello3").build());

	}

	@Bean
	public PollableChannel queueChannel() {
		return new QueueChannel(10);
	}

	@Bean
	public SubscribableChannel directChannel() {
		return MessageChannels.direct("channel2").get();
	}

}

