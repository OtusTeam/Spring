package ru.otus.spring.repostory;

import ru.otus.spring.domain.Email;

import java.util.List;

public interface EmailRepository {

    List<Email> findAll();

    List<Email> findByEmailLike(String name);

    Email save(Email email);
}
