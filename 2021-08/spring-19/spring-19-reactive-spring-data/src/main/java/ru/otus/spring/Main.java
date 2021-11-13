package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.AccountRepository;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository repository = context.getBean(PersonRepository.class);
        AccountRepository accountRepository = context.getBean(AccountRepository.class);

        var persons = List.of(
                new Person("Pushkin"),
                new Person("Lermontov"));

        // subscribe блокирует текущий поток и дожидается Flux
        repository.saveAll(persons)
                .subscribe();

        // а вот это уже неблокирующий subscribe
        repository.findAll()
                .map(Person::getName)
                .subscribe(System.out::println);

        // Пример объединения двух Flux
        Flux.merge(repository.findAll(), repository.findAll())
                .map(Person::getName)
                .subscribe(System.out::println);

        Thread.sleep(20000);
    }
}
