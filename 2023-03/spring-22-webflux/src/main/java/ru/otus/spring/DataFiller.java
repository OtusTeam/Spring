package ru.otus.spring;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

@Component
public class DataFiller implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataFiller.class);

    private final PersonRepository personRepository;

    public DataFiller(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        personRepository.saveAll(Arrays.asList(
                new Person("Pushkin", 22),
                new Person("Lermontov", 22),
                new Person("Tolstoy", 60)
        )).subscribe(savedPerson -> logger.info("saved person:{}", savedPerson));
    }
}
