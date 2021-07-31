package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import ru.otus.spring.domain.Email;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class);


        PersonRepository personRepository = ctx.getBean(PersonRepository.class);

        personRepository.save(new Person(1, "Pushkin"));
        personRepository.save(new Person(2, "Lermontov"));
        personRepository.save(new Person(0, "Pehov"));

        System.out.println("Все пёрсоны");
        System.out.println(personRepository.findAll());

/*
        EmailRepository emailRepository = ctx.getBean(EmailRepository.class);

        emailRepository.save(new Email(1, "alex@pushkin.com"));
        emailRepository.save(new Email(2, "nashe_vsie@pushkin.com"));
        emailRepository.save(new Email(3, "micha@lermontov.com"));

        System.out.println("Все адреса почты");
        System.out.println(emailRepository.findAll());
*/

    }
}
