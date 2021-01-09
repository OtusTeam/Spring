package ru.otus.spring11.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring11.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByName(Person person);


}
