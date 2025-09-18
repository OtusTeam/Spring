package ru.otus.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import ru.otus.spring.domain.Email;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.EmailRepository;
import ru.otus.spring.repostory.PersonRepository;

@SpringBootApplication
@EnableMapRepositories
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    @Autowired
    private EmailRepository emailRepository;

    @PostConstruct
    public void init() {
        repository.save(new Person(1, "Pushkin"));
        repository.save(new Person(2, "Lermontov"));
        System.out.println(repository.findAll());

        emailRepository.save(new Email(1, "alex@pushkin.com"));
        emailRepository.save(new Email(2, "micha@pushkin.com"));
        System.out.println(emailRepository.findAll());
    }
}
