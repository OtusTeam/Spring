package ru.otus.spring.service;

import ru.otus.spring.domain.FullBookInfo;

import java.util.List;

public interface LibraryService {

    List<FullBookInfo> getAllBooks();

    FullBookInfo getBookById(long id);

    FullBookInfo getBookByName(String name);

    FullBookInfo addNewBook(String bookName, String authorName, String genreName);

    void deleteBookById(long id);

    void deleteBookByName(String name);
}
