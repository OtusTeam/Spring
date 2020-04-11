package ru.otus.spring11.repostory;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring11.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    Person findByName(String s);
}
