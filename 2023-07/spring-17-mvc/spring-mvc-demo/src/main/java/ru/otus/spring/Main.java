package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Main {

    // http://localhost:8080/server/system/info

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Person(1, "Pushkin"));
    }
}
