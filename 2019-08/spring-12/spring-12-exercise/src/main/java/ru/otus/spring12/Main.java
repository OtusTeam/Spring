package ru.otus.spring12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import ru.otus.spring12.domain.Person;
import ru.otus.spring12.repostory.PersonRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Person("Pushkin"));

        repository.findAll();
    }
}
