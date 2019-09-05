package ru.otus.example.mongodbdemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.mongodbdemo.model.Knowledge;

public interface KnowledgeRepository extends MongoRepository<Knowledge, String> {
}
