package ru.otus.example.mongodbdemo.repositories;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.example.mongodbdemo.model.Student;


import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @Data
    private class ArraySizeProjection {
        private int size;
    }

    private final MongoTemplate mongoTemplate;

    public long getExperienceArrayLengthByStudentId(String id) {
        val aggregation = Aggregation.newAggregation(
                Aggregation.match(where("id").is(id)),
                Aggregation.project().andExclude("_id").and("experience").size().as("size"));

        val arraySizeProjection = mongoTemplate.aggregate(aggregation, Student.class, ArraySizeProjection.class).getUniqueMappedResult();
        return arraySizeProjection == null ? 0 : arraySizeProjection.getSize();
    }

    public void removeExperienceArrayElementsById(String id) {
        val query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        val update = new Update().pull("experience", query);
        mongoTemplate.updateMulti(new Query(), update, Student.class);
    }

}
