package ru.otus.spring.test.gateway;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class GatewayApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(GatewayApp.class, args);
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
		Upcase upcase = ctx.getBean(Upcase.class);
		Collection<String> result = upcase.upcase(Arrays.asList("test", "new", "last"));
		log.warn("Upcase result: {}", result);

	}

	@MessagingGateway
	public interface Upcase {
		@Gateway(requestChannel = "upcase.input")
		Collection<String> upcase(Collection<String> strings);
	}

	@Bean
	public IntegrationFlow upcase() {
		return f -> f//.channel("from-input-to-split")
				.split()
//				.split(list -> list.getObject().spliterator())
//				.split(getCustomSplitter(), "split")
				.channel("from-split-to-transformer")
				.<String, String>transform(String::toUpperCase)
				.channel("from-transformer-to-aggregate")
				.aggregate();
	}


//	@Bean
//	CustomSplitter getCustomSplitter() {
//		return new CustomSplitter();
//	}
//
//	public static class CustomSplitter {
//		public Collection<String> split(Message<Collection<String>> message) {
//			return message.getPayload().stream().skip(1).collect(Collectors.toList());
//		}
//	}


}
