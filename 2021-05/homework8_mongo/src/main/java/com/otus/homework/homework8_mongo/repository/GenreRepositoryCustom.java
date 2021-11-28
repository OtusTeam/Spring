package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Genre;

public interface GenreRepositoryCustom {
    Genre saveWithBooks(Genre genre);
}
