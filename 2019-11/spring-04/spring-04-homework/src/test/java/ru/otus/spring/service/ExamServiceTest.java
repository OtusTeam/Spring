package ru.otus.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ExamServiceTest {
    @Mock
    private QuestionPresentationService questionPresentationService;

    @Mock
    private QuestionService questionService;

    private ExamService examService;

    @BeforeEach
    void setUp() {
        given(questionService.getQuestionList()).willReturn(
                new ArrayList<>(
                        List.of(
                                new Question("Q1", List.of("A1", "A2", "A3"), 1),
                                new Question("Q2", List.of("A4", "A5", "A6"), 2),
                                new Question("Q3", List.of("A7", "A8", "A9", "A10"), 4)
                        )
                ));
        given(questionPresentationService.getUserName()).willReturn("Name");
        given(questionPresentationService.getUserSurname()).willReturn("Surname");
        given(questionPresentationService.getUserAnswer(any())).willReturn(true);
        examService = new ExamServiceImpl(questionService, questionPresentationService, 3, 3);
    }

    @Test
    @DisplayName("Proceed Exam test")
    void proceedExamTest() {
        assertThat(examService.proceedExam()).isEqualTo(true);
    }
}
