package ru.otus.work.service;

import ru.otus.work.domain.CommentBook;

import java.util.List;

public interface CommentBookService {

    void save(CommentBook commentBook);

    List<CommentBook> findByBookId(String bookId);
}
