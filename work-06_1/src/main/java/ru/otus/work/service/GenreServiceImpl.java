package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.dao.GenreDao;
import ru.otus.work.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre save(Genre genre) {

        if (genre == null) {
            return null;
        }

        Genre genreByName = genreDao.getByName(genre.getName());

        if (genreByName == null) {
            return genreDao.insert(genre);
        } else {
            return genreByName;
        }
    }

    @Override
    public List<Genre> listAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreDao.getById(id);
    }
}
