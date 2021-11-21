package com.otus.homework.homework8_mongo.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Comment;
import com.otus.homework.homework8_mongo.domain.Genre;
import com.otus.homework.homework8_mongo.repository.AuthorRepository;
import com.otus.homework.homework8_mongo.repository.BookRepository;
import com.otus.homework.homework8_mongo.repository.CommentRepository;
import com.otus.homework.homework8_mongo.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;


@ChangeLog
public class InitMongoDBChangelog {
    private Author hHarrison;
    private Author rBradbury;
    private Author rHeinlein;
    private Genre novel;
    private Genre scienceFiction;
    private Book steelRat;
    private Book dandelionWine;
    private Book doorSummer;


    //удаление базы
    @ChangeSet(author = "lenu", id = "dropDB", order = "001", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    //добавляем авторов в базу
    @ChangeSet(author = "lenu", id = "initAuthors", order = "002", runAlways = true)
    public void insertAuthors(AuthorRepository repository) {
        hHarrison = repository.save(new Author("Harry Harrison"));
        rBradbury = repository.save(new Author("Ray Bradbury"));
        rHeinlein = repository.save(new Author("Robert Heinlein"));
    }

    //добавляем жанры в базу
    @ChangeSet(author = "lenu", id = "initGenres", order = "003")
    public void insertGenre(GenreRepository repository) {
        novel = repository.save(new Genre("novel"));
        scienceFiction = repository.save(new Genre("science fiction"));
    }

    //добавляем книги в базу
    @ChangeSet(author = "lenu", id = "initBooks", order = "004", runAlways = true)
    public void insertBooks(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        steelRat = repository.save(new Book("Steel rat", hHarrison, novel));
        dandelionWine = repository.save(new Book("Dandelion wine", rBradbury, novel));
        doorSummer = repository.save(new Book("Door into summer", rHeinlein, scienceFiction));

    }

    //добавляем комментарии в базу
    @ChangeSet(author = "lenu", id = "initComments", order = "005", runAlways = true)
    public void insertBooks(CommentRepository repository, BookRepository bookRepository) {
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment("I don't like this book and I haven't read it", "Gregory", steelRat, 10L);
        repository.save(comment1);
        comments.add(comment1);
        bookRepository.save(steelRat);

        Comment comment2 = new Comment("Wonderful novel", "Mary Chang", dandelionWine, 10L);
        repository.save(comment2);
        Comment comment3 = new Comment("Just another boring comment", "Peter", dandelionWine, 5L);
        repository.save(comment3);
        Comment comment4 = new Comment("Very bad comment", "Josef", dandelionWine, 1L);
        repository.save(comment4);
        comments.clear();
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        bookRepository.save(dandelionWine);
    }
}
