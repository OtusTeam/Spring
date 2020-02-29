package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.domain.Email;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.EmailRepository;
import ru.otus.spring.repository.PersonRepository;
//import ru.otus.spring10.repostory.PersonRepository;

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
        Person person = new Person("Pushkin", 20);
        Email email = new Email("test@mail.ru");
        person.setEmail(email);
        repository.save(person);

        repository.findByName("Pushkin").forEach(System.out::println);
        repository.findByNameAndAge("Pushkin", 20).forEach(System.out::println);

        Email email1 = new Email("test1@mail.ru");
        emailRepository.save(email1);
        emailRepository.findByEmail("test@mail.ru").forEach(System.out::println);


    }
}
