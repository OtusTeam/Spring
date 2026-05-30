package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "client")
public class ClientProperties {
	private String url;
	private String key;
}
