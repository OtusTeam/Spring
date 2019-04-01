package ru.otus.example.mongoDbDemo.repositories;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.example.mongoDbDemo.config", "ru.otus.example.mongoDbDemo.repositories"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
abstract class AbstractRepositoryTest {
}
