package ru.otus.work.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
