package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.save(new Person("Dostoevsky"));

        Thread.sleep(3000);

        System.out.println("\n\n\n----------------------------------------------\n\n");
        System.out.println("Авторы в БД:");
        repository.findAll().forEach(p -> System.out.println(p.getName()));
        System.out.println("\n\n----------------------------------------------\n\n\n");
    }
}
