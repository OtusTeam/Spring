package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.dao.BookDao;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public void save(String name,
                     String authorStr,
                     String genreStr,
                     String description) {
        Author author = null;
        if (authorStr != null && authorStr.length() > 0) {
            author = Author.builder()
                    .name(authorStr)
                    .build();
        }

        Genre genre = null;
        if (genreStr != null && genreStr.length() > 0) {
            genre = Genre.builder()
                    .name(genreStr)
                    .build();
        }

        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .description(description)
                .build();

        authorService.save(book.getAuthor());
        genreService.save(book.getGenre());
        bookDao.insert(book);
    }

    @Override
    public void modify(String number,
                       String name,
                       String authorStr,
                       String genreStr,
                       String description) {
        Author author = null;
        if (authorStr != null && authorStr.length() > 0) {
            author = Author.builder()
                    .name(authorStr)
                    .build();
        }

        Genre genre = null;
        if (genreStr != null && genreStr.length() > 0) {
            genre = Genre.builder()
                    .name(genreStr)
                    .build();
        }

        Book book = Book.builder()
                .id(Long.valueOf(number))
                .name(name)
                .author(author)
                .genre(genre)
                .description(description)
                .build();

        book.setAuthor(authorService.save(book.getAuthor()));
        book.setGenre(genreService.save(book.getGenre()));
        bookDao.update(book);
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> listAll() {
        return bookDao.getAll();
    }

    @Override
    public Book findById(Long id) {
        return bookDao.getById(id);
    }
}
