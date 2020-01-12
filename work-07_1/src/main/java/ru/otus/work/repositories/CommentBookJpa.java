package ru.otus.work.repositories;

import ru.otus.work.domain.CommentBook;

import java.util.List;

public interface CommentBookJpa {
    CommentBook save(CommentBook commentBook);

    List<CommentBook> findByBookId(Long bookId);
}
