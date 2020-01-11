package ru.otus.spring.repositorytest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.Question;
import ru.otus.spring.repository.QuestionRepository;
import ru.otus.spring.repository.QuestionRepositoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("Question Repository QueryAll test")
    void testQueryAll() {
        List<String[]> testString = new ArrayList<>();
        testString.add(new String[]{"Q1", "A1", "A2", "1"});
        testString.add(new String[]{"Q2", "A3", "A4", "A5", "2"});

        List<Question> questionList = questionRepository.queryAll(testString);
        assertThat(questionList.size()).isEqualTo(2);
        assertThat(questionList.get(0).getQuestionText()).isEqualTo("Q1");
        assertThat(questionList.get(1).getAnswers().size()).isEqualTo(3);
    }

    @Configuration
    static class NestedConfiguration {
        @Bean
        QuestionRepository getQuestionRepository() {
            return new QuestionRepositoryImpl("");
        }
    }
}
