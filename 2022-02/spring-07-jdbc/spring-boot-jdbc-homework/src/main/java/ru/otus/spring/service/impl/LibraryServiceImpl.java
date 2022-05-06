package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.*;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dao.*;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"classpath:application.yml"})
public class LibraryServiceImpl implements LibraryService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookAuthorLinkDao bookAuthorLinkDao;
    private final BookGenreLinkDao bookGenreLinkDao;

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> result = new ArrayList<>();
        bookDao.getAll().forEach(book -> result.add(createBookDto(book)));
        return result;
    }

    @Override
    public BookDto getBookById(long id) {
        return createBookDto(bookDao.getById(id));
    }

    @Override
    public BookDto getBookByName(String name) {
        return createBookDto(bookDao.getByName(name));
    }

    @Override
    public BookDto addNewBook(String bookName, String authorName, String genreName) {
        bookDao.insert(bookName);
        Book book = bookDao.getByName(bookName);
        String[] authorArray = authorName.split("\\,");
        Arrays.stream(authorArray).forEach(a -> {
            Author author = authorDao.getByName(a);
            if (author == null) {
                authorDao.insert(a);
                author = authorDao.getByName(a);
            }

            bookAuthorLinkDao.insert(book.getId(), author.getId());
        });

        String[] genreArray = authorName.split("\\,");
        Arrays.stream(genreArray).forEach(g -> {
            Genre genre = genreDao.getByName(g);
            if (genre == null) {
                genreDao.insert(g);
                genre = genreDao.getByName(g);
            }

            bookGenreLinkDao.insert(book.getId(), genre.getId());
        });

        return createBookDto(book);
    }

    @Override
    public void deleteBookById(long id) {
        Book book = bookDao.getById(id);
        if (book != null) {
            deleteBook(book);
            out.println("Книга c id=" + id + " успешно удалена!");
        } else {
            out.println("Книга c id=" + id + " не найдена!");
        }
    }

    @Override
    public void deleteBookByName(String name) {
        Book book = bookDao.getByName(name);
        if (book != null) {
            deleteBook(book);
            out.println("Книга c name=" + name + " успешно удалена!");
        } else {
            out.println("Книга c name=" + name + " не найдена!");
        }
    }

    private BookDto createBookDto(Book book) {
        BookDto dto = null;
        if (book != null) {
            dto = new BookDto();
            dto.setId(book.getId());
            dto.setName(book.getName());
            dto.setAuthorList(authorDao.getAllByBookId(book.getId()));
            dto.setGenreList(genreDao.getAllByBookId(book.getId()));
        }

        return dto;
    }

    private void deleteBook(Book book) {
        bookAuthorLinkDao.deleteAllByBookId(book.getId());
        bookGenreLinkDao.deleteAllByBookId(book.getId());
        bookDao.deleteById(book.getId());
    }

}
