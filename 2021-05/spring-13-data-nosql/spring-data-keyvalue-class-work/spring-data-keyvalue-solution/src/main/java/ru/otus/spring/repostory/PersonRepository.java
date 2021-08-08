package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonRepository extends KeyValueRepository<Person, Integer> {

    List<Person> findAll();
}
