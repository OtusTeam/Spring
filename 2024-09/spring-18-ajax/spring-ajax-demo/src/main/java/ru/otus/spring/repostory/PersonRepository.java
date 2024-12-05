package ru.otus.spring.repostory;

import org.springframework.data.repository.ListCrudRepository;
import ru.otus.spring.domain.Person;

public interface PersonRepository extends ListCrudRepository<Person, Long> {
}
