package ru.otus.example.mongodbdemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.mongodbdemo.model.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String>, TeacherRepositoryCustom {
}
