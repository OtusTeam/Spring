package ru.otus.junit.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.otus.work.dao.QuestionDao;
import ru.otus.work.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Тест QuestionDao")
public class QuestionDaoTest {

    private final String text = "text";
    private final String answer = "answer";

    // TODO Почему-то не работает @Mock?
    QuestionDao mockQuestionDao = mock(QuestionDao.class);

    @BeforeEach
    public void setUp() {
        List<Question> listQuestions = new ArrayList<>();

        Question question = new Question();
        question.setText(text);
        question.setAnswer(answer);

        listQuestions.add(question);

        when(mockQuestionDao.listQuestions()).thenReturn(listQuestions);
    }

    @Test
    void testRead() {
        assertThat(mockQuestionDao.listQuestions().size()).isEqualTo(1);
        assertThat(mockQuestionDao.listQuestions().get(0).getText()).isEqualTo(text);
        assertThat(mockQuestionDao.listQuestions().get(0).getAnswer()).isEqualTo(answer);
    }
}
