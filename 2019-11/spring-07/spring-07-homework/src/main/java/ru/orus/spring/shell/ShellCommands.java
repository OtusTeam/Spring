package ru.orus.spring.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.orus.spring.dao.AuthorDao;
import ru.orus.spring.dao.BooksDao;
import ru.orus.spring.dao.GenreDao;
import ru.orus.spring.domain.Author;
import ru.orus.spring.domain.Book;
import ru.orus.spring.domain.Genre;

@ShellComponent
public class ShellCommands {
    private final BooksDao booksDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    public ShellCommands(BooksDao booksDao, AuthorDao authorDao, GenreDao genreDao) {
        this.booksDao = booksDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "get books list", key = {"books", "b"})
    public List<Book> getBooks() {
        return booksDao.getAll();
    }

    @ShellMethod(value = "add book", key = {"ba", "book_add"})
    public void addBook(@ShellOption() String caption) {
        booksDao.addBook(caption);
    }

    @ShellMethod(value = "delete book by id", key = {"book_delete", "bd"})
    public void deleteBook(@ShellOption() long id) {
        booksDao.deleteBook(id);
    }

    @ShellMethod(value = "get book info by id", key = {"book_info", "bi"})
    public String bookInfo(@ShellOption() long id) {
        return booksDao.bookInfo(id);
    }

    @ShellMethod(value = "add book's author by id", key = {"book_author_add", "baa"})
    public void bookAuthorAdd(@ShellOption() long bookId, @ShellOption() long authorId) {
        booksDao.addBookAuthor(bookId, authorId);
    }

    @ShellMethod(value = "delete book's author by id", key = {"book_author_delete", "bad"})
    public void bookAuthorDelete(@ShellOption() long bookId, @ShellOption() long authorId) {
        booksDao.deleteBookAuthor(bookId, authorId);
    }

    @ShellMethod(value = "add book's genre by id", key = {"book_genre_add", "bga"})
    public void bookGenreAdd(@ShellOption() long bookId, @ShellOption() long genreId) {
        booksDao.addBookGenre(bookId, genreId);
    }

    @ShellMethod(value = "delete book's genre by id", key = {"book_genre_delete", "bgd"})
    public void bookGenreDelete(@ShellOption() long bookId, @ShellOption() long genreId) {
        booksDao.deleteBookGenre(bookId, genreId);
    }

    @ShellMethod(value = "get authors list", key = {"authors", "a"})
    public List<Author> getAuthors() {
        return authorDao.getAll();
    }

    @ShellMethod(value = "add author", key = {"author_add", "aa"})
    public void addAuthor(@ShellOption() String name) {
        authorDao.addAuthor(name);
    }

    @ShellMethod(value = "delete author by id", key = {"author_delete", "ad"})
    public void deleteAuthor(@ShellOption() long authorId) {
        authorDao.deleteAuthor(authorId);
    }

    @ShellMethod(value = "get genres list", key = {"genres", "g"})
    public List<Genre> getGenres() {
        return genreDao.getAll();
    }

    @ShellMethod(value = "add genre", key = {"genre_add", "ga"})
    public void addGenre(@ShellOption() String name) {
        genreDao.addGenre(name);
    }

    @ShellMethod(value = "delete genre by id", key = {"genre_delete", "gd"})
    public void deleteGenre(@ShellOption() long genreId) {
        genreDao.deleteGenre(genreId);
    }
}
