package ru.otus.spring.testing.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс SimpleQuestionImp")
public class simpleQuestionTest {
    private static final SimpleQuestion question = new SimpleQuestionImp("The third planet from the Sun", "Earth");
    @Test
    @DisplayName("корректно создается конструктором")
    void shouldHaveCorrectConstructor(){
        assertEquals("The third planet from the Sun", question.getQuestion());

    }

    @Test
    @DisplayName("воспринимает ответ совпадающий с правльным как правильный")
    void shouldAcceptCorrectAnswer(){
        question.giveAnswer("Earth");
        assert question.check();
    }

    @Test
    @DisplayName("воспринимает правильный ответ как правильный не зависимо от регистра")
    void shouldAcceptCorrectAnswerNoCase(){
        question.giveAnswer("earth");
        assert question.check();
    }

    @Test
    @DisplayName("воспринимает неправильный ответ как неправильный")
    void shouldNotAcceptWrongAnswer(){
        question.giveAnswer("Mars");
        assert !question.check();
    }
}
