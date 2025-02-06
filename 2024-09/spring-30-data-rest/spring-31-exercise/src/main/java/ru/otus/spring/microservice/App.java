package ru.otus.spring.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.annotation.PostConstruct;
import ru.otus.spring.microservice.domain.Person;
import ru.otus.spring.microservice.repostory.PersonRepository;


@SpringBootApplication
@EnableWebMvc
public class App {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @PostConstruct
    public void init() {
        for(int i = 0 ; i < 1000; ++i) {
            repository.save(new Person("Ivan"));
            repository.save(new Person("Maria"));
        }
    }
}
