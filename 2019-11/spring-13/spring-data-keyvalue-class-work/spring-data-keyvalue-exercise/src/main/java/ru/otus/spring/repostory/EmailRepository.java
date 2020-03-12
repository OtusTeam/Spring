package ru.otus.spring.repostory;

import java.util.List;

import ru.otus.spring.domain.Email;

public interface EmailRepository  {
    List<Email> findAll();
    Email save(Email email);
}
