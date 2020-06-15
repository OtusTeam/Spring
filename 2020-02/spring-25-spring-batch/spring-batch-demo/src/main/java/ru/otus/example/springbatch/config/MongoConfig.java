package ru.otus.example.springbatch.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.example.springbatch.chandgelogs.InitMongoDBDataChangeLog;

@Configuration
public class MongoConfig {

    @Autowired
    private AppProps appProps;

    @Bean
    public Mongock mongock(MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, appProps.getMongoDBName(),
                InitMongoDBDataChangeLog.class.getPackageName())
                .build();
    }
}
