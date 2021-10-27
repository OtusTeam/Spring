package com.example.homework6_jpa.service;

import com.example.homework6_jpa.dao.BookDao;
import com.example.homework6_jpa.dao.CommentDao;
import com.example.homework6_jpa.domain.Book;
import com.example.homework6_jpa.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentDao repository;
    private final BookDao bookRepository;

    public CommentServiceImpl(@Autowired CommentDao repository, @Autowired BookDao bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByBook(long bookId) {
       Book book = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
       return book.getComments();
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Comment comment) {
        repository.save(comment);
    }
}
