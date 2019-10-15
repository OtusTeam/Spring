package ru.otus.spring12.repostory;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring12.domain.Person;

import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();
}
