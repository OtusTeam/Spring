package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.GenreJpa;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreJpa genreJpa;

    public GenreServiceImpl(GenreJpa genreJpa) {
        this.genreJpa = genreJpa;
    }

    @Override
    public Genre save(Genre genre) {

        if (genre == null) {
            return null;
        }

        List<Genre> genres = genreJpa.getByName(genre.getName());

        if (genres.isEmpty()) {
            genre.setId(null);
            return genreJpa.save(genre);
        } else {
            return genres.get(0);
        }
    }

    @Override
    public Genre findById(Long id) {
        Optional<Genre> optionalGenre = genreJpa.getById(id);
        return optionalGenre.orElse(null);
    }
}
