package ru.otus.spring.dao;

public interface BookAuthorLinkDao {

    void insert(long bookId, long authorId);

    void deleteAllByBookId(long bookId);
}
