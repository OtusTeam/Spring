package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.domain.Person;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.insert(new Person("Вася"));
        repository.insert(new Person("Юля"));
        Person vasya = repository.getFirst();
        System.out.println(vasya);

        List<Person> vasyaAndJulia = repository.getAll();
        System.out.println(vasyaAndJulia);


        Console.main(args);
    }
}
