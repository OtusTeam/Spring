package ru.otus.testingdemo.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Параллельное выполнение заработает только при настроенном в junit-platform.properties
// junit.jupiter.execution.parallel.enabled=true.
// Для проверки см. название потока теста в консоли
@Execution(ExecutionMode.CONCURRENT)
class WindDirectionConverterParametrizedUnitTest {

    private WindDirectionConverter converter;

    @BeforeEach
    void setUp() {
        converter = new WindDirectionConverter();
    }

    @DisplayName("Should convert given degree to expected direction")
    @ParameterizedTest(name = "Should convert {0} degree to {1} direction")
    @CsvSource({"45,NE", "90,E", "135,SE", "180,S", "225,SW", "270,W", "315,NW", "360,N"})
    void shouldConvertDegreeToExpectedDirection(int degree, String expectedDirection) {
        System.out.println("Текущий поток: %s".formatted(Thread.currentThread().getName()));
        var actualDirection = converter.convert(degree);
        assertThat(actualDirection).isEqualTo(expectedDirection);
    }












    //@MethodSource("getTestParams")
    public static Stream<Arguments> getTestParams() {
        return Stream.of(
                Arguments.of(0, "N"),
                Arguments.of(360, "N"),
                Arguments.of(90, "E"),
                Arguments.of(180, "S"),
                Arguments.of(270, "W"),
                Arguments.of(45, "NE"),
                Arguments.of(135, "SE"),
                Arguments.of(225, "SW"),
                Arguments.of(315, "NW")
        );
    }

}