package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.repository.AuthorRepository;
import com.otus.homework.homework8_mongo.repository.BookRepository;
import com.otus.homework.homework8_mongo.repository.GenreRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(@Autowired BookRepository repository,
                           @Autowired AuthorRepository authorRepository,
                           @Autowired GenreRepository genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> getById(String bookId) {
        return repository.findById(bookId);
    }

    @Override
    public List<Book> getByTitle(String title) {
        return repository.getByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return repository.getAllByAuthor(author.getId());
    }

    @Override
    public void delete(String bookId) {
        repository.deleteById(bookId);

    }

//    @Override
//    public Book saveWithConstraints(Book book) {
//        repository.save(book);
//        Author author = book.getAuthor();
//        List<Book> books = author.getBooks() == null ? new ArrayList<>(): author.getBooks();
//        books.add(book);
//        author.setBooks(books);
//        authorRepository.save(author);
//        Genre genre = book.getGenre();
//        books = genre.getBooks()== null ? new ArrayList<>():genre.getBooks();
//        books.add(book);
//        genre.setBooks(books);
//        genreRepository.save(genre);
//        repository.saveWithConstraints(book);
//        return book;
//    }

//    @Override
//    public Book updateTitleById(String bookId, String title) throws NotFoundException {
//        Book book = repository.findById(bookId).orElseThrow(() -> new NotFoundException("There is no book with id = "+ bookId));
//        book.setTitle(title);
//        //обновить книгу в списке автора
//        Author author = book.getAuthor();
//        List<Book> books = author.getBooks() == null ? new ArrayList<>(): author.getBooks();
//        books.remove(bookId);
//        books.add(book);
//        author.setBooks(books);
//        authorRepository.save(author);
//        //обновить книгу в списке жанра
//        Genre genre = book.getGenre();
//        books = genre.getBooks()== null ? new ArrayList<>():genre.getBooks();
//        books.remove(bookId);
//        books.add(book);
//        genre.setBooks(books);
//        genreRepository.save(genre);
//        repository.saveWithConstraints(book);
//        return book;
//    }

//    @Override
//    public long countAttachedComments(String bookId) throws NotFoundException {
//        Book book = repository.findById(bookId).orElseThrow(() -> new NotFoundException("There is no book with id = "+ bookId));
//        return book.getComments().size();
//    }

    @Override
    public void removeBookWithComments(String bookId) {
        repository.removeBookWithComments(bookId);

    }
}
