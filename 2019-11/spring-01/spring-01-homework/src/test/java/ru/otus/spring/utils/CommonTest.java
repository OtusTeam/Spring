package ru.otus.spring.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.utils.Common.parseQuestionsArray;

@DisplayName("Тестирование утилитного класса")
public class CommonTest {
    @Test
    @DisplayName("Получение вопросов из списка строк")
    void parseQuestionsArrayTest() {
        List<String[]> testString = new ArrayList<>();
        testString.add(new String[] {"Q1", "A1", "A2", "1"});
        testString.add(new String[] {"Q2", "A3", "A4", "A5", "2"});

        List<Question> questionList = parseQuestionsArray(testString);
        assertThat(questionList.size()).isEqualTo(2);
        assertThat(questionList.get(0).getQuestionText()).isEqualTo("Q1");
        assertThat(questionList.get(1).getAnswers().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Получение вопросов из списка неправильных строк")
    void parseWrongQuestionsArrayTest() {
        List<String[]> testString = new ArrayList<>();
        testString.add(new String[] {"Q1", "A1", "A2", "ф"});

        List<Question> questionList = parseQuestionsArray(testString);
        assertThat(questionList.size()).isEqualTo(0);
    }
}
