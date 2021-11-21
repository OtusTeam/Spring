package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Comment;
import com.otus.homework.homework8_mongo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    public CommentServiceImpl(@Autowired CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> getById(String commentId) {
        return repository.findById(commentId);
    }

    @Override
    public List<Comment> getByBook(Book book) {
        return repository.getAllByBook(book.getId());
    }

    @Override
    public void deleteById(String commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }
}
