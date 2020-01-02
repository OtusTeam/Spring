package ru.otus.work.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.controller.TestController;
import ru.otus.work.dao.AnswerDao;
import ru.otus.work.dao.QuestionDao;
import ru.otus.work.service.InputOutputService;
import ru.otus.work.service.LocalizedMessage;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Тест класса TestController")
public class TestControllerTest {

    @MockBean
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @MockBean
    private InputOutputService inputOutputService;

    @MockBean
    private LocalizedMessage localizedMessage;

    @Autowired
    private TestController testController;

    private static final String QUESTION = "QuestionText";
    private static final String ANSWER = "QuestionAnswer";
    private static final String ANSWER_FROM_USER = "AnswerQuestion";

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);

        Question question = new Question(QUESTION, ANSWER);
        List<Question> questionList = Collections.singletonList(question);
        given(questionDao.listQuestions()).willReturn(questionList);

        given(inputOutputService.readMessage()).willReturn(ANSWER_FROM_USER);

        given(localizedMessage.getMessage(eq("helloMessage"), isNull())).willReturn("My name is Ivan!");
    }

    @Test
    public void controllerTest() {
        testController.runTest();

        assertThat(answerDao.listAnswers().size()).isNotZero();
        assertThat(answerDao.listAnswers().size()).isEqualTo(1);
        assertThat(answerDao.listAnswers().get(0).getAnswer()).isEqualTo(ANSWER_FROM_USER);
        assertThat(answerDao.listAnswers().get(0).getQuestion()).isEqualTo(QUESTION);
    }
}
