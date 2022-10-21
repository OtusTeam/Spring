package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Email;
import ru.otus.spring.domain.Person;

import java.util.List;

public interface EmailRepository  {

    List<Email> findAll();
    Email save(Email email);
}
