package ru.otus.spring.microservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.microservice.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
