package ru.otus.spring12.repostory;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring12.domain.Email;
import ru.otus.spring12.domain.Person;

import java.util.List;

public interface EmailRepository  {

    List<Email> findAll();
    Email save(Email email);
}
