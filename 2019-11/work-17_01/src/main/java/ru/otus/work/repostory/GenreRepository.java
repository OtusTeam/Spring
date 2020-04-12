package ru.otus.work.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.work.domain.Genre;

import java.util.List;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @RestResource(rel = "genres", path="genres")
    List<Genre> findByName(String name);
}
