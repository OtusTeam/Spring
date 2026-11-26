package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.out;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"classpath:application.yml"})
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        List<BookDto> result = new ArrayList<>();
        bookRepository.findAll().forEach(book -> result.add(new BookDto(book)));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllBookName() {
        return bookRepository.findAll().stream().map(Book::getName).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return new BookDto(book);
        } else {
            out.printf("Книга с id=%d не найдена%n", id);
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookByName(String name) {
        Book book = bookRepository.findByName(name);
        if (book != null) {
            return new BookDto(book);
        } else {
            out.printf("Книга с name=%s не найдена%n", name);
            return null;
        }
    }

    @Override
    @Transactional
    public BookDto addNewBook(String bookName, String authorName, String genreName, String commentText) {
        Book book = bookRepository.findByName(bookName);
        if (book == null) {
            Author author = authorRepository.findByName(authorName);
            if (author == null) {
                author = authorRepository.save(new Author(authorName));
            }

            Genre genre = genreRepository.findByName(genreName);
            if (genre == null) {
                genre = genreRepository.save(new Genre(genreName));
            }

            Comment comment = commentRepository.save(new Comment(commentText));
            book = bookRepository.save(new Book(bookName, Set.of(comment), Set.of(author), Set.of(genre)));
            out.println("Успешно добавлена книга!");
        } else {
            out.println("Книга с таким названием уже существует!");
        }

        return new BookDto(book);
    }

    @Override
    @Transactional
    public void updateBookNameById(long id, String name) {
        bookRepository.findById(id).ifPresentOrElse(book -> {
            book.setName(name);
            bookRepository.save(book);
            out.println("Успешно изменено название книги!");
        }, () -> out.printf("Книга с id=%d не найдена%n", id));
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
        out.printf("Книга с id=%d удалена%n", id);
    }

    @Override
    @Transactional
    public void deleteBookByName(String name) {
        bookRepository.deleteByName(name);
        out.printf("Книга с name=%s удалена%n", name);
    }

}
