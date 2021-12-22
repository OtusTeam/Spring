package ru.otus.spring.microservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.microservice.domain.Person;

import java.util.List;

// Эта аннотация позволяет изменить имя ресурса
@RepositoryRestResource(path = "person")
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);
}
