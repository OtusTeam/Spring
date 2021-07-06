package ru.otus.spring11.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring11.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();

    Person findByName(String s);

    Person findByEmail_Email(String email);
}
