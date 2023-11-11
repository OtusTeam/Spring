package ru.otus.spring.test.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

//@SpringBootApplication
@IntegrationComponentScan
public class FlowBridge {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(FlowBridge.class, args);
		MessageChannel flowInput = ctx.getBean("flowSimpleBridge.input", MessageChannel.class);
		QueueChannel p2pChannel = ctx.getBean("queueCh", QueueChannel.class);

		flowInput.send(MessageBuilder.withPayload("Hello New").build());
		Message<?> receive = p2pChannel.receive();
		System.out.println(receive);
	}


	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		return Pollers
				.fixedRate(1000).getObject();
	}

	@Bean
	public IntegrationFlow flowWithPullerBridge() {
		return f -> f.channel(
						MessageChannels.queue("queueChannel", 10))
				.channel(MessageChannels
						.publishSubscribe("pubSubChannel"));
	}


	@Bean
	public IntegrationFlow flowSimpleBridge() {
		return f -> f
				.channel(
						MessageChannels.queue(
								"queueCh", 10
						)
				);
	}
}
