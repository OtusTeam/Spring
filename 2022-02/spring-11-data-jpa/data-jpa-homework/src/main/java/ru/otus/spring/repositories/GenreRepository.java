package ru.otus.spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.models.Genre;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

    Genre findByName(String name);
}
