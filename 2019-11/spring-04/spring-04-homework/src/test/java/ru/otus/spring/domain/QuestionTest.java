package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    @Test
    @DisplayName("Empty Question test")
    void testEmptyQuestion() {
        Question question = new Question();
        assertThat(question.getQuestionText()).isEqualTo(null);
        assertThat(question.getCorrectAnswer()).isEqualTo(null);
        assertThat(question.getAnswers()).isEqualTo(null);
    }

    @Test
    @DisplayName("Non-empty Question test")
    void testNonemptyQuestion() {
        Question question = new Question(
                "Question",
                new ArrayList<String>(List.of("Answer 1", "Answer 2", "Answer 3")),
                1
        );
        assertThat(question.getQuestionText()).isEqualTo("Question");
        assertThat(question.getCorrectAnswer()).isEqualTo(1);
        assertThat(question.getAnswers()).containsExactly("Answer 1", "Answer 2", "Answer 3");
    }
}
