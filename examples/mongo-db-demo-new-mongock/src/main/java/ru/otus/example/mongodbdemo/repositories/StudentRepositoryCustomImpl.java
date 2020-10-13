package ru.otus.example.mongodbdemo.repositories;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.example.mongodbdemo.model.Knowledge;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.utils.RawResultPrinter;


import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ObjectOperators.ObjectToArray.valueOfToArray;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @Data
    private class ArraySizeProjection {
        private int size;
    }

    private final MongoTemplate mongoTemplate;
    private final RawResultPrinter rawResultPrinter;

    @Override
    public List<Knowledge> getStudentExperienceById(String studentId) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("id").is(studentId))
                , unwind("experience")
                , project().andExclude("_id").and(valueOfToArray("experience")).as("experience_map")
                , project().and("experience_map").arrayElementAt(1).as("experience_id_map")
                , project().and("experience_id_map.v").as("experience_id")
                , lookup("knowledge", "experience_id", "_id", "experience")
                , project().and("experience._id").as("_id").and("experience.name").as("name")
        );

        Document rawResults = mongoTemplate.aggregate(aggregation, Student.class, Knowledge.class).getRawResults();
        rawResultPrinter.prettyPrintRawResult(rawResults);
        return mongoTemplate.aggregate(aggregation, Student.class, Knowledge.class).getMappedResults();
    }

    @Override
    public long getExperienceArrayLengthByStudentId(String studentId) {
        val aggregation = Aggregation.newAggregation(
                match(where("id").is(studentId)),
                project().andExclude("_id").and("experience").size().as("size"));

        val arraySizeProjection =
                mongoTemplate.aggregate(aggregation, Student.class, ArraySizeProjection.class).getUniqueMappedResult();
        return arraySizeProjection == null ? 0 : arraySizeProjection.getSize();
    }

    @Override
    public void removeExperienceArrayElementsById(String id) {
        val query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        val update = new Update().pull("experience", query);
        mongoTemplate.updateMulti(new Query(), update, Student.class);
    }

}
