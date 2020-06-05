package ru.otus.example.springbatch.chandgelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.example.springbatch.model.Person;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initPersons", author = "stvort", runAlways = true)
    public void initPersons(MongoTemplate template){
        template.save(new Person("John", 21));
        template.save(new Person("Ivan", 32));
        template.save(new Person("Dima", 52));
        template.save(new Person("Mike", 22));
        template.save(new Person("Duke", 33));
        template.save(new Person("John", 21));
        template.save(new Person("Ivan", 32));
        template.save(new Person("Dima", 52));
        template.save(new Person("Mike", 22));
        template.save(new Person("Duke", 33));
        template.save(new Person("John", 21));
        template.save(new Person("Ivan", 32));
        template.save(new Person("Dima", 52));
        template.save(new Person("Mike", 22));
        template.save(new Person("Duke", 33));
        template.save(new Person("John", 21));
        template.save(new Person("Ivan", 32));
        template.save(new Person("Dima", 52));
        template.save(new Person("Mike", 22));
        template.save(new Person("Duke", 33));
        template.save(new Person("John", 21));
        template.save(new Person("Ivan", 32));
        template.save(new Person("Dima", 52));
        template.save(new Person("Mike", 22));
        template.save(new Person("Duke", 33));
    }
}
