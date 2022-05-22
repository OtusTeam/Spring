package ru.otus.spring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookDto;

import java.util.List;

public interface LibraryService {

    List<BookDto> getAllBooks();

    List<String> getAllBookName();

    BookDto getBookById(long id);

    BookDto getBookByName(String name);

    BookDto addNewBook(String bookName, String authorName, String genreName, String commentText);

    void updateBookNameById(long id, String name);

    void deleteBookById(long id);

    void deleteBookByName(String name);
}
