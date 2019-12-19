package ru.otus.junit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.work.domain.Answer;
import ru.otus.work.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест класса Question")
public class QuestionTest {

    @DisplayName("Проверка set и get")
    @Test
    void shouldHaveCorrectConstructor() {
        String answer = "answer", text = "text";

        Question question  = new Question();
        question.setAnswer(answer);
        question.setText(text);

        assertThat(question.getAnswer()).isEqualTo(answer);
        assertThat(question.getText()).isEqualTo(text);
    }
}
