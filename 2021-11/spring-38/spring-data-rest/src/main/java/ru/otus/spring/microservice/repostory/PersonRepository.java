package ru.otus.spring.microservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.microservice.domain.Person;

import java.util.List;

@RepositoryRestResource(path = "persons", collectionResourceRel = "persons")
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
