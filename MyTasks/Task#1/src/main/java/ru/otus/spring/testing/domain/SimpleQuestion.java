package ru.otus.spring.testing.domain;

public interface SimpleQuestion
{
    String getQuestion();
    void giveAnswer(String answer);
    boolean check();
}
