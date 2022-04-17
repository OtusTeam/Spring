package com.example.hw14_batch.changelogs;

import com.example.hw14_batch.model.nosql.Author;
import com.example.hw14_batch.model.nosql.Book;
import com.example.hw14_batch.model.nosql.Genre;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDBChangelog {
    private Author hHarrison;
    private Author rBradbury;
    private Author rHeinlein;

    private Genre novel;
    private Genre scienceFiction;
    private Genre story;

    @ChangeSet(order = "000", id = "dropDB", runAlways = true, author = "lenu")
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", runAlways = true, author = "lenu")
    public void initAuthors(MongockTemplate template){
        hHarrison = template.save(new Author("Harry Harrison"));
        rBradbury = template.save(new Author("Ray Bradbury"));
        rHeinlein = template.save(new Author("Robert Heinlein"));
    }

    @ChangeSet(order = "002", id = "initGenre", runAlways = true, author = "lenu")
    public void initGenres(MongockTemplate template) {
        novel = template.save(new Genre("novel"));
        scienceFiction = template.save(new Genre("science fiction"));
        story = template.save(new Genre("story"));
    }

    @ChangeSet(order = "003", id = "initBooks", runAlways = true, author = "lenu")
    public void initBooks(MongockTemplate template) {
        template.save(new Book("Steel rat", hHarrison, story));
        template.save(new Book("Dandelion wine", rBradbury, novel));
        template.save(new Book("Door into summer", rHeinlein, scienceFiction));

    }

}
