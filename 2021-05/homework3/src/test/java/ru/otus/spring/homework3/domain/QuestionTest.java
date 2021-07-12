package ru.otus.spring.homework3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        List<Answer> answers = Arrays.asList(
                new Answer(1, 0,"0", true) ,
                new Answer(1, 1, "0b", false),
                new Answer(1, 2, "null", false),
                new Answer(1, 3, "not defined", false));
        Question question = new Question(1, "What is the default value of byte variable?", answers);
        assertAll("question",
                () -> assertEquals(1, question.getId()),
                () -> assertEquals("What is the default value of byte variable?", question.getQuestionText()),
                () -> assertEquals(4, question.getAnswers().size()),
                () -> assertTrue(question.getAnswers().get(0).isRightAnswer())
        );
    }


}