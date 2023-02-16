package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

import java.util.Arrays;

@SpringBootApplication
public class WebfluxDemo {
    private static final Logger logger = LoggerFactory.getLogger(WebfluxDemo.class);

    public static void main(String[] args) {
        var context = SpringApplication.run(WebfluxDemo.class);
        var repository = context.getBean(PersonRepository.class);

        repository.saveAll(Arrays.asList(
                new Person("Pushkin", 22),
                new Person("Lermontov", 22),
                new Person("Tolstoy", 60)
        )).subscribe(p -> logger.info("person name:{}", p.getLastName()));
    }
}

