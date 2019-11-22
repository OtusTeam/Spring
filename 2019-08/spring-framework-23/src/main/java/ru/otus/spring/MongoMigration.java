package ru.otus.spring;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MongoMigration {
    private final MongoTemplate mongoTemplate;
    
    public MongoMigration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @PostConstruct
    public void init() {
        mongoTemplate.save(new Person("John", 21));
        mongoTemplate.save(new Person("Ivan", 32));
        mongoTemplate.save(new Person("Dima", 52));
        mongoTemplate.save(new Person("Mike", 22));
        mongoTemplate.save(new Person("Duke", 33));
        mongoTemplate.save(new Person("John", 21));
        mongoTemplate.save(new Person("Ivan", 32));
        mongoTemplate.save(new Person("Dima", 52));
        mongoTemplate.save(new Person("Mike", 22));
        mongoTemplate.save(new Person("Duke", 33));
        mongoTemplate.save(new Person("John", 21));
        mongoTemplate.save(new Person("Ivan", 32));
        mongoTemplate.save(new Person("Dima", 52));
        mongoTemplate.save(new Person("Mike", 22));
        mongoTemplate.save(new Person("Duke", 33));
        mongoTemplate.save(new Person("John", 21));
        mongoTemplate.save(new Person("Ivan", 32));
        mongoTemplate.save(new Person("Dima", 52));
        mongoTemplate.save(new Person("Mike", 22));
        mongoTemplate.save(new Person("Duke", 33));
        mongoTemplate.save(new Person("John", 21));
        mongoTemplate.save(new Person("Ivan", 32));
        mongoTemplate.save(new Person("Dima", 52));
        mongoTemplate.save(new Person("Mike", 22));
        mongoTemplate.save(new Person("Duke", 33));
    }
}
