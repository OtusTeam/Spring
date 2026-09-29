package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.PersonRepository;

import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository personRepository = context.getBean(PersonRepository.class);


        personRepository.save(new Person("Александр Сергеевич Пушкин"));
        personRepository.save(new Person("Михаил Юрьевич Лермонтов"));
        personRepository.save(new Person("Михаил Сергеевич Горбачев"));

        System.out.println("\n\nИщем всех пёрсонов");
        System.out.println(personRepository.findAll().stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("\n\nИщем Пушкина");
        personRepository.findByName("Александр Сергеевич Пушкин")
                .ifPresent(System.out::println);


        System.out.println("\n\n");

    }
}
