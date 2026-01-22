package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.*;
import ru.otus.spring.domain.FullBookInfo;
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

    private final FullBookInfoDao fullBookInfoDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookAuthorLinkDao bookAuthorLinkDao;
    private final BookGenreLinkDao bookGenreLinkDao;

    @Override
    public List<FullBookInfo> getAllBooks() {
        return fullBookInfoDao.getAll();
    }

    @Override
    public FullBookInfo getBookById(long id) {
        return fullBookInfoDao.getById(id);
    }

    @Override
    public FullBookInfo getBookByName(String name) {
        return fullBookInfoDao.getByName(name);
    }

    @Override
    public FullBookInfo addNewBook(String bookName, String authorName, String genreName) {
        bookDao.insert(bookName);
        Book book = bookDao.getByName(bookName);
        String[] authorArray = authorName.split("\\,");
        List<Author> authorList = new ArrayList<>();
        Arrays.stream(authorArray).forEach(a -> {
            Author author = authorDao.getByName(a);
            if (author == null) {
                authorDao.insert(a);
                author = authorDao.getByName(a);
            }

            authorList.add(author);
            bookAuthorLinkDao.insert(book.getId(), author.getId());
        });

        String[] genreArray = authorName.split("\\,");
        List<Genre> genreList = new ArrayList<>();
        Arrays.stream(genreArray).forEach(g -> {
            Genre genre = genreDao.getByName(g);
            if (genre == null) {
                genreDao.insert(g);
                genre = genreDao.getByName(g);
            }

            genreList.add(genre);
            bookGenreLinkDao.insert(book.getId(), genre.getId());
        });

        return new FullBookInfo(book.getId(), book.getName(), authorList, genreList);
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

    private void deleteBook(Book book) {
        bookAuthorLinkDao.deleteAllByBookId(book.getId());
        bookGenreLinkDao.deleteAllByBookId(book.getId());
        bookDao.deleteById(book.getId());
    }

}
