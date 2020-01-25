package ru.otus.work.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final AuthorRepository authorRepository;

    public BookRepositoryCustomImpl(MongoTemplate mongoTemplate, AuthorRepository authorRepository) {
        this.mongoTemplate = mongoTemplate;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> result = Collections.emptyList();
        List<Author> authors = authorRepository.findByName(author);
        if (!authors.isEmpty()) {
            Query query = new Query();
            for (Author authorFind : authors) {
                query.addCriteria(Criteria.where("authors").is(authorFind.getId()));
            }
            result = new ArrayList<>(mongoTemplate.find(query, Book.class));
        }
        return result;
    }
}
