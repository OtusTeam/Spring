package ru.otus.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.configs.ExamProps;
import ru.otus.spring.domain.Topic;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование репозитория вопросов")
public class TopicRepositoryImplTest {
    private TopicRepositoryImpl questionRepository;

    @BeforeEach
    void setUp() {
        ExamProps examProps = new ExamProps();
        questionRepository = new TopicRepositoryImpl(examProps);
    }

    @Test
    @DisplayName("Получение вопросов из списка строк")
    void queryAllTest() {
        List<String[]> testString = new ArrayList<>();
        testString.add(new String[]{"Q1", "A1", "A2", "1"});
        testString.add(new String[]{"Q2", "A3", "A4", "A5", "2"});

        List<Topic> topicList = questionRepository.getAll(testString);
        assertThat(topicList.size()).isEqualTo(2);
        assertThat(topicList.get(0).getQuestionText()).isEqualTo("Q1");
        assertThat(topicList.get(1).getAnswers().size()).isEqualTo(3);
    }
}
