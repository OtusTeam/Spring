package ru.otus.example.mongodbdemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.mongodbdemo.model.Student;


public interface StudentRepository extends MongoRepository<Student, String>, StudentRepositoryCustom {
}
