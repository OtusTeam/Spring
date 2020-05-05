package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.CommentBook;
import ru.otus.work.repositories.CommentBookRepository;

@Service
public class CommentBookServiceImpl implements CommentBookService {

    private final CommentBookRepository commentBookRepository;

    public CommentBookServiceImpl(CommentBookRepository commentBookRepository) {
        this.commentBookRepository = commentBookRepository;
    }

    @Override
    public void save(CommentBook commentBook) {
        commentBookRepository.save(commentBook);
    }
}
