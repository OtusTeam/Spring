package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.CommentBook;
import ru.otus.work.repositories.CommentBookJpa;

@Service
public class CommentBookServiceImpl implements CommentBookService {

    private final CommentBookJpa commentBookJpa;

    public CommentBookServiceImpl(CommentBookJpa commentBookJpa) {
        this.commentBookJpa = commentBookJpa;
    }

    @Override
    public void save(CommentBook commentBook) {
        commentBookJpa.save(commentBook);
    }
}
