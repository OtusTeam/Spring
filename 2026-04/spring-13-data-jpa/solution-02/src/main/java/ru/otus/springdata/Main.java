package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springdata.domain.Email;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.EmailRepository;
import ru.otus.springdata.repository.PersonRepository;

import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        var pushkinEmail = new Email("alex.pushkin@mail.ru");
        var lermontovEmail = new Email("michail.lermontov@mail.ru");
        var gorbachevEmail = new Email("gorbachev@mail.ru");

        var pushkin = new Person("Александр Сергеевич Пушкин");
        var lermontov = new Person("Михаил Юрьевич Лермонтов");
        var gorbachev = new Person("Михаил Сергеевич Горбачев");

        emailRepository.save(pushkinEmail);
        emailRepository.save(lermontovEmail);
        emailRepository.save(gorbachevEmail);

        personRepository.save(pushkin);
        personRepository.save(lermontov);
        personRepository.save(gorbachev);

        System.out.println("\n\nИщем всех пёрсонов");
        System.out.println(personRepository.findAll().stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("\n\nИщем Пушкина");
        personRepository.findByName("Александр Сергеевич Пушкин")
                .ifPresent(System.out::println);

        System.out.println("\n\nИщем все почты");
        System.out.println(emailRepository.findAll().stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("\n\n");
    }
}
