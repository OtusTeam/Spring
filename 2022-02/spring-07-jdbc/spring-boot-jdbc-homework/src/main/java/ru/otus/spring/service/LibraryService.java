package ru.otus.spring.service;

import ru.otus.spring.dto.BookDto;

import java.util.List;

public interface LibraryService {

    List<BookDto> getAllBooks();

    BookDto getBookById(long id);

    BookDto getBookByName(String name);

    BookDto addNewBook(String bookName, String authorName, String genreName);

    void deleteBookById(long id);

    void deleteBookByName(String name);
}
