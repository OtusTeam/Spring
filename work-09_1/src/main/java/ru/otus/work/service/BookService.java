package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Book;

import java.util.List;

@Service
public interface BookService {
    void save(String id,
              String name,
              List<String> authorStr,
              String genreStr,
              String description);

    void delete(String id);

    List<Book> listAll();

    Book findById(String id);

    List<Book> findByAuthor(String author);

    void addComment(String bookId, String comment);
}
