package ru.otus.spring.testing.dao;

import ru.otus.spring.testing.domain.SimpleQuestion;
import ru.otus.spring.testing.domain.SimpleQuestionImp;

public class TestQuestionDAOMock implements TestQuestionDAO{
    @Override
    public SimpleQuestion getByNumber(int number) {
        return new SimpleQuestionImp("How many days in a week", "7");
    }

    @Override
    public int getQuestionsAmount() {
        return 1;
    }
}
