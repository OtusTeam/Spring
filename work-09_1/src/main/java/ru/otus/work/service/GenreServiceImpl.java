package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) {

        if (genre == null) {
            return null;
        }

        List<Genre> genres = genreRepository.findByName(genre.getName());

        if (genres.isEmpty()) {
            genre.setId(null);
            return genreRepository.save(genre);
        } else {
            return genres.get(0);
        }
    }

    @Override
    public Genre findById(String id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        return optionalGenre.orElse(null);
    }

    @Override
    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }
}
