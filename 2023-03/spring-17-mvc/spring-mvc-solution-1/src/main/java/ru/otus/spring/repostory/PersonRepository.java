package ru.otus.spring.repostory;

import org.springframework.data.repository.ListCrudRepository;
import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonRepository extends ListCrudRepository<Person, Long> {

    List<Person> findAll();
    List<Person> findByName(String name);
}
