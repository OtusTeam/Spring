package ru.otus.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class ApplicationConfig {


    @Bean
    public ApplicationMessage applicationMessage(@Value("${application.message}") String message) {
        return new ApplicationMessage(message);
    }
}
