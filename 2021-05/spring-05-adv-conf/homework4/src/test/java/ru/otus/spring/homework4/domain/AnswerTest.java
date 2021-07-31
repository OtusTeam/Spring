package ru.otus.spring.homework4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Answer")
class AnswerTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Answer answer = new Answer(1, 1, "0 byte", true);
        assertAll("answer",
                () -> assertEquals(1, answer.getQuestionId()),
                () -> assertEquals(1, answer.getAnswerId()),
                () -> assertEquals("0 byte", answer.getAnswerText()),
                () -> assertTrue(answer.isRightAnswer())
        );
    }

    @DisplayName("корректно формируется строка")
    @Test
    void shouldHaveCorrectString() {
        Answer answer = new Answer(1, 1, "0 byte", true);
        assertEquals(""+'\t'+ "1. 0 byte", answer.toString());
    }


}