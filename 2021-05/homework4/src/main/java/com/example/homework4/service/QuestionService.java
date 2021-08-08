package com.example.homework4.service;


import com.example.homework4.domain.Question;


public interface QuestionService {
    void run();
    void printQuestions();
    boolean isTestPassed();
    boolean checkAnswer(Question question, int answerId);
    void returnResult();
}
