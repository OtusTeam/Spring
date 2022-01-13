package ru.otus.spring.repostory;

import ru.otus.spring.domain.Email;

import java.util.List;

public interface EmailRepository {

    List<Email> findAll();

    Email save( Email email );
}
