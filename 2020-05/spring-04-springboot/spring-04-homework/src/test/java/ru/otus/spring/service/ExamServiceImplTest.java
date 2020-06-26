package ru.otus.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.configs.ExamProps;
import ru.otus.spring.domain.Topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ExamServiceImplTest {
    @Mock
    private TopicPresentationService topicPresentationService;

    @Mock
    private TopicService topicService;

    private ExamServiceImpl examService;

    @BeforeEach
    void setUp() {
        given(topicService.getTopicList()).willReturn(
                new ArrayList<>(
                        List.of(
                                new Topic("Q1", List.of("A1", "A2", "A3"), 1),
                                new Topic("Q2", List.of("A4", "A5", "A6"), 2),
                                new Topic("Q3", List.of("A7", "A8", "A9", "A10"), 4)
                        )
                ));
        given(topicPresentationService.getUserName()).willReturn("Name");
        given(topicPresentationService.getUserSurname()).willReturn("Surname");
        given(topicPresentationService.getUserAnswer(any())).willReturn(true);
        ExamProps examProps = new ExamProps();
        examProps.setTotalquestioncount(3L);
        examProps.setPassquestioncount(2L);
        examService = new ExamServiceImpl(topicService, topicPresentationService, examProps);
    }

    @Test
    void proceedExamTest() {
        assertThat(examService.proceedExam()).isBetween(0.9, 1.1);
    }
}
