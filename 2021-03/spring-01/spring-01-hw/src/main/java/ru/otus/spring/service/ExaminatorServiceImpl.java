package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;

public class ExaminatorServiceImpl implements ExaminatorService{
    private final QuestionService questionService;
    private final QuestionDao questionDao;

    public ExaminatorServiceImpl(QuestionService questionService, QuestionDao questionDao) {
        this.questionService = questionService;
        this.questionDao = questionDao;
    }

    @Override
    public Long testing() {
        return this.questionDao.findAll().stream()
                .map(this.questionService::checkQuestion)
                .filter(iter -> iter.equals(true))
                .count();
    }
}
