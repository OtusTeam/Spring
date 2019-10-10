package ru.otus.example.mongodbdemo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.example.mongodbdemo.model.Knowledge;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.model.Teacher;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Knowledge springDataKnowledge;
    private Knowledge mongockKnowledge;
    private Knowledge aggregationApiKnowledge;

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initKnowledges", author = "stvort", runAlways = true)
    public void initKnowledges(MongoTemplate template){
        springDataKnowledge = template.save(new Knowledge("Spring Data"));
        mongockKnowledge = template.save(new Knowledge("Mongock"));
        aggregationApiKnowledge = template.save(new Knowledge("AggregationApi"));
    }

    @ChangeSet(order = "002", id = "initStudents", author = "stvort", runAlways = true)
    public void initStudents(MongoTemplate template){
        template.save(new Student("Student #1", springDataKnowledge, mongockKnowledge));
    }

    @ChangeSet(order = "003", id = "Teacher", author = "stvort", runAlways = true)
    public void initTeachers(MongoTemplate template){
        val teacher = new Teacher("Teacher #1", springDataKnowledge, mongockKnowledge, aggregationApiKnowledge);
        template.save(teacher);
    }
}
