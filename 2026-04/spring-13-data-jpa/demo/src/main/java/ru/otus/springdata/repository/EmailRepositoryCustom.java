package ru.otus.springdata.repository;

import ru.otus.springdata.domain.Email;

import java.util.Optional;

public interface EmailRepositoryCustom {
    Optional<Email> findByPersonId(long personId);
}
