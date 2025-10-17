package ru.otus.spring.testing.dao;

import ru.otus.spring.testing.domain.SimpleQuestion;

public interface TestQuestionDAO {
    SimpleQuestion getByNumber(int number) throws PersistentException;
    int getQuestionsAmount() throws PersistentException;
}
