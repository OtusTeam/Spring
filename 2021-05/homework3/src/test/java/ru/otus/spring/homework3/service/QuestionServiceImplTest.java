package ru.otus.spring.homework3.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.homework3.dao.QuestionDao;
import ru.otus.spring.homework3.dao.QuestionDaoSimple;
import ru.otus.spring.homework3.domain.Answer;
import ru.otus.spring.homework3.domain.Question;
import ru.otus.spring.homework3.util.Parser;
import ru.otus.spring.homework3.util.ParserImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private Parser parser;

    @Mock
    private QuestionDaoSimple questionDao;

    @InjectMocks
    private QuestionServiceImpl questionService;
    private MessageSource msg;

    @Test
    @DisplayName("корректно определяется результат теста")
    void shouldPassTest() {
        questionService = new QuestionServiceImpl(questionDao, 5, msg);
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
        // System.out.println(questionDao.getRightAnswer(question).toString());

        assertAll("answer",
                () -> assertTrue(questionDao.isAnswerRight(question, questionDao.getRightAnswer(question).getAnswerId())),
                () -> assertFalse(questionDao.isAnswerRight(question, 1))
        );

    }


}