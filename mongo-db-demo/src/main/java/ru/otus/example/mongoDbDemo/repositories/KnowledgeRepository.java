package ru.otus.example.mongoDbDemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.mongoDbDemo.model.Knowledge;

public interface KnowledgeRepository extends MongoRepository<Knowledge, String> {
}
