package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;

@SpringBootApplication
public class ReactiveSpringDataDemo {
    private static final Logger logger = LoggerFactory.getLogger(ReactiveSpringDataDemo.class);

    public static void main(String[] args) throws InterruptedException {
        var context = SpringApplication.run(ReactiveSpringDataDemo.class);

        var repository = context.getBean(PersonRepository.class);

        var persons = List.of(new Person("Pushkin"), new Person("Lermontov"));

        logger.info("before save");
        repository.saveAll(persons)
                        .doOnNext(savedPers -> logger.info("savedPers:{}", savedPers))
                .subscribe();
        logger.info("after save");

        // можно ничего и не найти
        repository.findAll()
                .map(Person::getName)
                .subscribe(name -> logger.info("person name:{}", name));

        // Пример объединения двух Flux
        Flux.merge(repository.findAll(), repository.findAll())
                .map(Person::getName)
                .subscribe(name -> logger.info("join name:{}", name));

        Thread.sleep(60_000);
    }
}
