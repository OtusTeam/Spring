package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Book;
import com.example.homework7_datajpa.model.Comment;
import com.example.homework7_datajpa.repository.BookRepository;
import com.example.homework7_datajpa.repository.CommentRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository repository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(@Autowired CommentRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Comment> getByBook(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
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
