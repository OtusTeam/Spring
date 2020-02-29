package ru.otus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameAndAge(String name, long age);
}
