package ru.otus.spring11.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring11.domain.Email;
import ru.otus.spring11.domain.Person;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Integer> {
    List<Email> findAll();

}
