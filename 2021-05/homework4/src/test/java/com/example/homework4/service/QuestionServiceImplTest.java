package com.example.homework4.service;

import com.example.homework4.dao.QuestionDaoSimple;
import com.example.homework4.domain.Answer;
import com.example.homework4.domain.Question;
import com.example.homework4.util.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceImplTest {
    @MockBean
    private Parser parser;

    @MockBean
    private QuestionDaoSimple questionDao;

    @MockBean
    private MessageService ms;

    @MockBean
    private ConsoleIOService ios;

    @Autowired
    private QuestionServiceImpl questionService;

    @Test
    @DisplayName("корректно определяется результат теста")
    void shouldPassTest() {
        questionService = new QuestionServiceImpl(questionDao, ms, ios);
        questionService.setTotalPoints(5);
        assertThat(questionService.isTestPassed()).isTrue();
    }

    @Test
    @DisplayName("Корректно определяется правильность ответа")
    void shouldDefineRightAnswer() {

        questionDao = new QuestionDaoSimple(parser);
        List<Answer> answers = Arrays.asList(
                new Answer(1, 0, "0", true),
                new Answer(1, 1, "0b", false),
                new Answer(1, 2, "null", false),
                new Answer(1, 3, "not defined", false));
        Question question = new Question(1, "What is the default value of byte variable?", answers);

        assertAll("answer",
                () -> assertTrue(questionDao.isAnswerRight(question, questionDao.getRightAnswer(question).getAnswerId())),
                () -> assertFalse(questionDao.isAnswerRight(question, 1))
        );

    }


}