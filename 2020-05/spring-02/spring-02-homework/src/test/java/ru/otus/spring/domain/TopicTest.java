package ru.otus.spring.domain;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TopicTest {

    @Test
    void getQuestionText() {
        String question = "Question";
        Topic topic = new Topic(question, Collections.emptyList(), -1);
        assertThat(topic.getQuestionText()).isEqualTo(question);
    }

    @Test
    void getAnswers() {
        List<String> answers = List.of("Answer 1", "Answer 2", "Answer 3");
        Topic topic = new Topic("", answers, -1);
        assertThat(topic.getAnswers()).containsExactly(answers.toArray(String[]::new));
    }

    @Test
    void getCorrectAnswer() {
        Topic topic = new Topic("", Collections.emptyList(), 2);
        assertThat(topic.getCorrectAnswer()).isEqualTo(2);
    }
}
