package ru.otus.example.mongodbdemo.repositories;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.example.mongodbdemo.config", "ru.otus.example.mongodbdemo.repositories"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
abstract class AbstractRepositoryTest {
}
