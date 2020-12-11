package ru.otus.spring.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.otus.spring.microservice.domain.Person;
import ru.otus.spring.microservice.repostory.PersonRepository;

import javax.annotation.PostConstruct;

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
