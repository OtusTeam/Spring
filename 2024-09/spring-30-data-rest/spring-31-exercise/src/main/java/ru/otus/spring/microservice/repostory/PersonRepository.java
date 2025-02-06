package ru.otus.spring.microservice.repostory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.otus.spring.microservice.domain.Person;

@RepositoryRestResource(path = "person")
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Override
    List<Person> findAll();

    @RestResource(path = "names")
    List<Person> findByName(String name);
}
