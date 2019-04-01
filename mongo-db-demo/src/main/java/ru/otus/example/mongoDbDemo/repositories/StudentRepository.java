package ru.otus.example.mongoDbDemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.mongoDbDemo.model.Student;


public interface StudentRepository extends MongoRepository<Student, String>, StudentRepositoryCustom {
}
