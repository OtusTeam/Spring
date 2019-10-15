package ru.otus.spring12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring12.domain.Person;
import ru.otus.spring12.repostory.PersonRepository;

import javax.annotation.PostConstruct;

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
