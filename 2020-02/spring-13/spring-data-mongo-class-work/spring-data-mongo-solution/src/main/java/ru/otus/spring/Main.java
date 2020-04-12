package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

@EnableMongoRepositories
@SpringBootApplication
public class Main {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.save(new Person("Pushkin"));

        Thread.sleep(3000);

        repository.findAll().forEach(p -> System.out.println(p.getName()));
    }
}
