package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.LibraryService;

import java.util.List;

import static java.lang.System.out;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"classpath:application.yml"})
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
        Book book = bookRepository.findById(id);
        if (book != null) {
            return book;
        } else {
            out.printf("Книга с id=%s не найдена%n", id);
            return null;
        }
    }

    @Override
    public Book getBookByName(String name) {
        Book book = bookRepository.findByName(name);
        if (book != null) {
            return book;
        } else {
            out.printf("Книга с name=%s не найдена%n", name);
            return null;
        }
    }

    @Override
    public Book addNewBook(String bookName, String authorName, String genreName, String commentText) {
        Book book = bookRepository.findByName(bookName);
        if (book == null) {
            book = bookRepository.save(new Book(bookName, List.of(new Comment(commentText)), List.of(new Author(authorName)), List.of(new Genre(genreName))));
            out.println("Успешно добавлена книга!");
        } else {
            out.println("Книга с таким названием уже существует!");
        }

        return book;
    }

    @Override
    public void updateBookNameById(String id, String name) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            book.setName(name);
            bookRepository.save(book);
            out.println("Успешно изменено название книги!");
        } else {
            out.printf("Книга с id=%s не найдена%n", id);
        }
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
        out.printf("Книга с id=%s удалена%n", id);
    }

    @Override
    public void deleteBookByName(String name) {
        bookRepository.deleteByName(name);
        out.printf("Книга с name=%s удалена%n", name);
    }

}
