package ru.otus.work.repositories;

import ru.otus.work.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findByAuthor(String author);
}
