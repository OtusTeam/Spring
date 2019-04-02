package ru.otus.example.mongodbdemo.repositories;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import ru.otus.example.mongodbdemo.model.Knowledge;
import ru.otus.example.mongodbdemo.model.Teacher;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
public class TeacherRepositoryCustomImpl implements TeacherRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Knowledge> getTeacherExperienceById(String teacherId) {
        val aggregation = newAggregation(
                match(Criteria.where("id").is(teacherId))
                , unwind("experience")
                , project().andExclude("_id").and("experience.id").as("_id").and("experience.name").as("name")
        );
        return mongoTemplate.aggregate(aggregation, Teacher.class, Knowledge.class).getMappedResults();
    }
}
