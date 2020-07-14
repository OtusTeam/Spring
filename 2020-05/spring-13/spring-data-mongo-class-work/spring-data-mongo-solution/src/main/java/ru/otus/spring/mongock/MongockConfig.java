package ru.otus.spring.mongock;

import com.github.cloudyrock.mongock.*;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MongockConfig {

    @Bean
    public SpringMongock mongock(MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, "company", "ru.otus.spring.mongock.changelog")
                .build();
    }
}
