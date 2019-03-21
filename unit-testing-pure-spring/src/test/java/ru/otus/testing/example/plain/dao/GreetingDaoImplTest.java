package ru.otus.testing.example.plain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.testing.example.dao.GreetingDao;
import ru.otus.testing.example.dao.GreetingDaoImpl;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Обчыный юнит-тест. Контекст spring не поднимается
@DisplayName("Дао для работы с приветствиями ")
class GreetingDaoImplTest {

    private GreetingDao greetingDao;

    @BeforeEach
    void setUp() {
        greetingDao = new GreetingDaoImpl("initial_data/greetings.properties");
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("дожен возвращать не пустое, корректное приветствие для кода указанной страны в любом регистре")
    void shouldReturnNotEmptyGreetingByCaseInsensitiveCountryCode(String countryCode, String expectedGreeting) {
        Optional<String> greetingOptional = greetingDao.findGreetingByCountryCode(countryCode);
        assertThat(greetingOptional).isNotEmpty().hasValue(expectedGreeting);
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("ru", "Тестовый привет"),
                Arguments.of("RU", "Тестовый привет"),
                Arguments.of("en", "Тестовый hello"),
                Arguments.of("EN", "Тестовый hello"),
                Arguments.of("cn", "Тестовый nihao"),
                Arguments.of("CN", "Тестовый nihao")
        );
    }
}