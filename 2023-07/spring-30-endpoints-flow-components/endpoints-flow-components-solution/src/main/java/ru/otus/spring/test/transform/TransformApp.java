package ru.otus.spring.test.transform;

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
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class TransformApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TransformApp.class, args);
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
		Collection<Item> result = upcase.upcase(Arrays.asList(new Item("test"), new Item("new"), new Item("last")));
//		Collection<String> result = upcase.upcase(Arrays.asList("test", "new", "last"));
		log.warn("Upcase result: {}", result);

	}

	public static class Item {
		public Item() {
		}

		public Item(String value) {
			this.value = value;
		}

		String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Item{" +
					"value='" + value + '\'' +
					'}';
		}
	}

	@MessagingGateway
	public interface Upcase {
		@Gateway(requestChannel = "upcase.input")
		Collection<Item> upcase(Collection<Item> strings);
	}

	@Bean
	public IntegrationFlow upcase() {
		return f -> f
				.split()
				.transform(Transformers.toMap())
				.<Map<String, String>, Map<String, String>>transform(map -> {
					map.replaceAll((k, v) -> v.toUpperCase());
					return map;
				})
				.transform(Transformers.fromMap(Item.class))
				.aggregate();
	}

}
