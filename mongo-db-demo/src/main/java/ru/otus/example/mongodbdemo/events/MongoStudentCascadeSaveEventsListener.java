package ru.otus.example.mongodbdemo.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.repositories.KnowledgeRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoStudentCascadeSaveEventsListener extends AbstractMongoEventListener<Student> {

    private final KnowledgeRepository knowledgeRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Student> event) {
        super.onBeforeConvert(event);
        val student = event.getSource();
        if (student.getExperience() != null) {
            student.getExperience().stream().filter(e -> Objects.isNull(e.getId())).forEach(knowledgeRepository::save);
        }
    }
}
