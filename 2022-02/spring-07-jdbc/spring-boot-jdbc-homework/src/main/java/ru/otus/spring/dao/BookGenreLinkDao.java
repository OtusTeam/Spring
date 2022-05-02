package ru.otus.spring.dao;

import ru.otus.spring.domain.BookGenreLink;

import java.util.List;

public interface BookGenreLinkDao {

    List<BookGenreLink> getAll();

    List<BookGenreLink> getAllByBookId(long id);

    List<BookGenreLink> getAllByGenreId(long id);

    void insert(long bookId, long genreId);

    void deleteById(long id);
}
