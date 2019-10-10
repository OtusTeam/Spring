package ru.otus.spring10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring10.domain.Email;
import ru.otus.spring10.domain.Person;
import ru.otus.spring10.repostory.EmailRepository;
import ru.otus.spring10.repostory.PersonRepository;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private PersonRepository repository;

    @Autowired
    private EmailRepository emailRepository;

    @PostConstruct
    public void init() {
        repository.save(new Person("Pushkin"));
        System.out.println(repository.findByName("Pushkin"));

        emailRepository.save(new Email("alex@pushkin.ru"));
        System.out.println(emailRepository.findAll());

    }
}
