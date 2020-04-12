package ru.otus.spring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.domain.Person;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void shouldSetIdOnSave() {
        Mono<Person> personMono = repository.save(new Person("Bill", 12));

        StepVerifier
                .create(personMono)
                .assertNext(person -> assertNotNull(person.getId()))
                .expectComplete()
                .verify();
    }
}
