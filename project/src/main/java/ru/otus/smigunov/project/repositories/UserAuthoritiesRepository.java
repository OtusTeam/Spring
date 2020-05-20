package ru.otus.smigunov.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.smigunov.project.domain.UserAuthorities;

@Repository
public interface UserAuthoritiesRepository extends JpaRepository<UserAuthorities, Long> {
    UserAuthorities findByUsernameAndAuthority(String username, String authority);
}