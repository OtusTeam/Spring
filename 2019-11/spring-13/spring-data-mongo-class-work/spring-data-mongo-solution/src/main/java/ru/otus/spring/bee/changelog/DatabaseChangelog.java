package ru.otus.spring.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addLermontov", author = "ydvorzhetskiy")
    public void insertLermontov(DB db) {
        DBCollection myCollection = db.getCollection("persons");
        BasicDBObject doc = new BasicDBObject().append("name", "Lermontov");
        myCollection.insert(doc);
    }
}
