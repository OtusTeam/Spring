package ru.otus.example.mongoDbDemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.mongoDbDemo.model.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String>, TeacherRepositoryCustom {
}
