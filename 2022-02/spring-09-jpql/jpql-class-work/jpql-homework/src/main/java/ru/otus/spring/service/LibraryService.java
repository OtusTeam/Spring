package ru.otus.spring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookDto;

import java.util.List;

public interface LibraryService {

    @Transactional(readOnly = true)
    List<BookDto> getAllBooks();

    @Transactional(readOnly = true)
    List<String> getAllBookName();

    @Transactional(readOnly = true)
    BookDto getBookById(long id);

    @Transactional(readOnly = true)
    BookDto getBookByName(String name);

    @Transactional
    BookDto addNewBook(String bookName, String authorName, String genreName, String commentText);

    @Transactional
    void updateBookNameById(long id, String name);

    @Transactional
    void deleteBookById(long id);

    @Transactional
    void deleteBookByName(String name);
}
