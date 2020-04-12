package ru.otus.example.rabbitmq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.useractivitymodels.AppUser;

@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
