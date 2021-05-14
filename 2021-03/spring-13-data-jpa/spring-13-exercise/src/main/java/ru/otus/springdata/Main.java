package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import ru.otus.spring10.repostory.PersonRepository;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        //PersonRepository personRepository = context.getBean(PersonRepository.class);
        //EmailRepository emailRepository = context.getBean(EmailRepository.class);

        // personRepository.save(new Person("Pushkin"));
        // personRepository.save(new Person("Lermontov"));
    }


}
