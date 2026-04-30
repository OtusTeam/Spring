package ru.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.demo.controller.InstanceId;

@Configuration
public class Config {

    @Bean
    InstanceId instanceId(@Value("${spring.application.instance_id}") String id) {
        return new InstanceId(id);
    }
}
