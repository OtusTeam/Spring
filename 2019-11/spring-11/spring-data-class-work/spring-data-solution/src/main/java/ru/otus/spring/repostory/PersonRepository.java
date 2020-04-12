package ru.otus.spring.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);
}
