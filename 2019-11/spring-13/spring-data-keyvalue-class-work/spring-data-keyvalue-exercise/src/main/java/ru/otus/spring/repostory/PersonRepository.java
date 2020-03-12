package ru.otus.spring.repostory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();
}
