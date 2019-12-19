package ru.otus.junit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.work.domain.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест класса Answer")
public class AnswerTest {

    @DisplayName("Проверка set и get")
    @Test
    void shouldHaveCorrectConstructor() {
        String answer = "answer", question = "question";

        Answer person = new Answer();
        person.setAnswer(answer);
        person.setQuestion(question);

        assertThat(person.getAnswer()).isEqualTo(answer);
        assertThat(person.getQuestion()).isEqualTo(question);
    }

}
