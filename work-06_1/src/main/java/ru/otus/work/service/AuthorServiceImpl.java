package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.dao.AuthorDao;
import ru.otus.work.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author save(Author author) {
        if (author == null) {
            return null;
        }

        Author authorByName = authorDao.getByName(author.getName());

        if (authorByName == null) {
            return authorDao.insert(author);
        } else {
            return authorByName;
        }
    }

    @Override
    public List<Author> listAll() {
        return authorDao.getAll();
    }

    @Override
    public Author findById(Long id) {
        return authorDao.getById(id);
    }
}
