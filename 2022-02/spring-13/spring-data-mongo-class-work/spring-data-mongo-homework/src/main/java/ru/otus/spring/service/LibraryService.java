package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface LibraryService {

    List<Book> getAllBooks();

    List<String> getAllBookName();

    Book getBookById(String id);

    Book getBookByName(String name);

    Book addNewBook(String bookName, String authorName, String genreName, String commentText);

    void updateBookNameById(String id, String name);

    void deleteBookById(String id);

    void deleteBookByName(String name);
}
