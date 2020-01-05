package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Book;

import java.util.List;

@Service
public interface BookService {
    void save(String name,
              String authorStr,
              String genreStr,
              String description);

    void modify(String number,
                String name,
                String authorStr,
                String genreStr,
                String description);

    void delete(Long id);

    List<Book> listAll();

    Book findById(Long id);
}
