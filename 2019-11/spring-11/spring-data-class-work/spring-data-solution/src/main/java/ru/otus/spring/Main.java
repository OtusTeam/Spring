package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private PersonRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Person("Pushkin"));

        repository.findByName("Pushkin").forEach(System.out::println);
    }
}
