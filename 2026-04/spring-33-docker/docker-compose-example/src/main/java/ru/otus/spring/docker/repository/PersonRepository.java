package ru.otus.spring.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.docker.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
