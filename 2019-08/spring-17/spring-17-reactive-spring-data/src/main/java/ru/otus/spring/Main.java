package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.saveAll(
                Flux.fromArray(new String[]{"Pushkin", "Lermontov"})
                        .map(Person::new)
        ).subscribe();

        Disposable flux = repository.findAll()
                .subscribe(p -> System.out.println(p.getName()));

        Thread.sleep(5000);

        flux.dispose();
    }
}
