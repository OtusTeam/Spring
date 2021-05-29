package ru.otus.junit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Person")
class PersonTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Person person = new Person(42, "Ivan");

        assertEquals("Ivan", person.getName());
        assertEquals(42, person.getAge());
    }

    // TODO: @DisplayName("должен")

    // TODO: @DisplayName("должен увеличивать возраст при вызове birthDay")
}
