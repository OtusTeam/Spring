package ru.otus.example.mongodbdemo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import ru.otus.example.mongodbdemo.model.Knowledge;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.model.Teacher;
import ru.otus.example.mongodbdemo.repositories.KnowledgeRepository;
import ru.otus.example.mongodbdemo.repositories.StudentRepository;
import ru.otus.example.mongodbdemo.repositories.TeacherRepository;

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
    public void initKnowledges(KnowledgeRepository repository){
        springDataKnowledge = repository.save(new Knowledge("Spring Data"));
        mongockKnowledge = repository.save(new Knowledge("Mongock"));
        aggregationApiKnowledge = repository.save(new Knowledge("AggregationApi"));
    }

    @ChangeSet(order = "002", id = "initStudents", author = "stvort", runAlways = true)
    public void initStudents(StudentRepository repository){
        repository.save(new Student("Student #1", springDataKnowledge, mongockKnowledge));
    }

    @ChangeSet(order = "003", id = "Teacher", author = "stvort", runAlways = true)
    public void initTeachers(TeacherRepository repository){
        val teacher = new Teacher("Teacher #1", springDataKnowledge, mongockKnowledge, aggregationApiKnowledge);
        repository.save(teacher);
    }
}
