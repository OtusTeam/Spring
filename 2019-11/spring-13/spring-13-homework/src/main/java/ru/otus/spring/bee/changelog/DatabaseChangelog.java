package ru.otus.spring.bee.changelog;

import java.util.Set;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "addAuthors", author = "evmaksimenko")
    public void insertAuthors(DB db) {
        DBCollection authorsCollecrtion = db.getCollection("authors");
        authorsCollecrtion.insert(new BasicDBObject().append("name", "Пелевин"));
        authorsCollecrtion.insert(new BasicDBObject().append("name", "Лафоре"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "evmaksimenko")
    public void insertGenres(DB db) {
        DBCollection authorsCollecrtion = db.getCollection("genres");
        authorsCollecrtion.insert(new BasicDBObject().append("name", "Фантастика"));
        authorsCollecrtion.insert(new BasicDBObject().append("name", "Компьютерная"));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "evmaksimenko")
    public void insertBooks(DB db) {
        DBCollection authorsCollecrtion = db.getCollection("books");
        authorsCollecrtion.insert(
                new BasicDBObject()
                        .append("caption", "Чапаев и пустота")
                        .append("authors", Set.of("Пелевин"))
                        .append("genres", Set.of("Фантастика"))
        );
        authorsCollecrtion.insert(
                new BasicDBObject()
                        .append("caption", "Структуры данных и алгоритмы Java")
                        .append("authors", Set.of("Лафоре"))
                        .append("genres", Set.of("Компьютерная"))
        );
    }

}
