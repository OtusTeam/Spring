package ru.otus.spring.test.upcase;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;

import lombok.extern.slf4j.Slf4j;

//@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class AppTest {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(AppTest.class, args);

		UpcaseService upcaseService = ctx.getBean(UpcaseService.class);
		Collection<String> upcaseStrings = upcaseService.upcaseStrings(
				List.of("1 message 1 ",
						"22 message message 22",
						"33 message message message 33"));
		log.warn("Upcase: {}", upcaseStrings);
		Thread.sleep(5000);
	}

	@MessagingGateway
	public interface UpcaseService {
		@Gateway(requestChannel = "upcase.input")
		Collection<String> upcaseStrings(Collection<String> strings);
	}

	@Bean
	public IntegrationFlow upcase() {
		return f -> f
				.split()
				.<String, String>transform(String::toUpperCase)
				.aggregate();
	}
}
