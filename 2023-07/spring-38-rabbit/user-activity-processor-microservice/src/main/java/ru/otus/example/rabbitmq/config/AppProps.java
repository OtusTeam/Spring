package ru.otus.example.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("app")
public class AppProps {
	private String serverEmail;
	private String adminEmail;
}
