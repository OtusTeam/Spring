package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repostory.AccountRepository;
import ru.otus.spring.repostory.PersonRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository repository = context.getBean(PersonRepository.class);
        AccountRepository accountRepository = context.getBean(AccountRepository.class);

        Thread.sleep(20000);
    }
}
