package ru.otus.work.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.work.domain.Author;

import java.util.List;

@RepositoryRestResource(path = "author")
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @RestResource(rel = "authors", path="authors")
    List<Author> findByName(String name);
}
