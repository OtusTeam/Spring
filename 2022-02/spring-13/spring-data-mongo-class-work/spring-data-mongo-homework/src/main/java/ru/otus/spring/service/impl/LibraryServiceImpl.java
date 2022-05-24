package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.LibraryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<String> getAllBookName() {
        return bookRepository.findAll().stream().map(Book::getName).toList();
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Книга с id=%s не найдена", id)));
    }

    @Override
    public Book getBookByName(String name) {
        return bookRepository.findByName(name).orElseThrow(() -> new RuntimeException(String.format("Книга с name=%s не найдена%n", name)));
    }

    @Override
    public Book addNewBook(String bookName, String authorName, String genreName, String commentText) {
        return bookRepository.findByName(bookName).orElseGet(() -> bookRepository.save(new Book(bookName, List.of(new Comment(commentText)), List.of(new Author(authorName)), List.of(new Genre(genreName)))));
    }

    @Override
    public void updateBookNameById(String id, String name) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Книга с id=%s не найдена", id)));
        book.setName(name);
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteBookByName(String name) {
        bookRepository.deleteByName(name);
    }

}
