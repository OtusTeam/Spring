package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Topic;

public interface TopicRepository {
    List<Topic> getAll();
    List<Topic> getAll(List<String[]> questionsArray);
}
