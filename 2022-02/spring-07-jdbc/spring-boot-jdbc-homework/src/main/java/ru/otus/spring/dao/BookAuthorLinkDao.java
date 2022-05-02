package ru.otus.spring.dao;

import ru.otus.spring.domain.BookAuthorLink;

import java.util.List;

public interface BookAuthorLinkDao {

    List<BookAuthorLink> getAll();

    List<BookAuthorLink> getAllByBookId(long id);

    List<BookAuthorLink> getAllByAuthorId(long id);

    void insert(long bookId, long authorId);

    void deleteById(long id);
}
