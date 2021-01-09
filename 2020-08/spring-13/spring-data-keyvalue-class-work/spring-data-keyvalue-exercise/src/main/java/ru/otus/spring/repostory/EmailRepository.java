package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.otus.spring.domain.Email;
import ru.otus.spring.domain.Person;

import java.util.List;


public interface EmailRepository {
    List<Email> findAll();
    Email save(Email email);
}
