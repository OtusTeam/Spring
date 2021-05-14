package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springdata.domain.Email;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.EmailRepository;
import ru.otus.springdata.repository.PersonRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        Email emailP = new Email("alex@pushkin.ru");
        emailP = emailRepository.save(emailP);

        Email emailL = new Email("michail@lermontov.ru");
        emailL = emailRepository.save(emailL);

        personRepository.save(new Person("Pushkin", emailP));
        personRepository.save(new Person("Lermontov",emailL));

        System.out.println("\n\nИщем Пушкина");
        personRepository.findByName("Pushkin").ifPresent(System.out::println);

        System.out.println("\n\nИщем всех пёрсонов");
        System.out.println(emailRepository.findAll());

        System.out.println("\n\nИщем Пушкина по его почте");
        personRepository.findByEmailAddress("alex@pushkin.ru").ifPresent(System.out::println);

        System.out.println("\n\nИщем почту Пушкина");
        emailRepository.findByEmailAddress("alex@pushkin.ru").ifPresent(System.out::println);

        System.out.println("\n\nОбновляем почту Лермонтову");
        System.out.println("До обновления: " + emailL);
        emailRepository.updateEmailById(emailL.getId(), "michail1984@lermontov.ru");
        emailRepository.findById(emailL.getId()).ifPresent(e -> System.out.println("После обновления: " + e));

        System.out.println("\n\n");

    }
}
