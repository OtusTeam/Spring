package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.domain.Person;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository repository = context.getBean(PersonRepository.class);

        Person julia = new Person("Юля");
        repository.insert(julia);
        Person juliaFromDB = repository.getByName(julia.getName());
        System.out.println(juliaFromDB);



        Console.main(args);
    }
}
