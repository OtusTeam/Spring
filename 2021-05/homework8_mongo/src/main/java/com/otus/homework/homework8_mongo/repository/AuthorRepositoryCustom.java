package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Author;

public interface AuthorRepositoryCustom {
    long getCountAuthorsBooks(String authorId);
    Author saveWithBooks(Author author);


}
