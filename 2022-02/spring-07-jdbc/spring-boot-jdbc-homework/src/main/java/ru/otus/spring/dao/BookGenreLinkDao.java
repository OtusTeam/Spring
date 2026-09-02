package ru.otus.spring.dao;

public interface BookGenreLinkDao {

    void insert(long bookId, long genreId);

    void deleteAllByBookId(long bookId);
}
