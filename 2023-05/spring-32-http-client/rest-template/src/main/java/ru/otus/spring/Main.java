package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.service.MainService;

@Slf4j
@EnableCaching
@EnableRetry
@EnableFeignClients
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Main.class, args);
		MainService service = ctx.getBean(MainService.class);
		service.start();

	}
}
