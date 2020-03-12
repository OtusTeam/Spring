package ru.otus.spring.repostory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Person;

public interface PersonRepository extends MongoRepository<Person, Integer> {

    List<Person> findAll();
}
