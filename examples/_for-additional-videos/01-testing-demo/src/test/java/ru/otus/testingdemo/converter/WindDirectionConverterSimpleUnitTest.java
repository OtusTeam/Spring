package ru.otus.testingdemo.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WindDirectionConverterSimpleUnitTest {

    private WindDirectionConverter converter;

    @BeforeEach
    void setUp() {
        converter = new WindDirectionConverter();
    }

    @DisplayName("Should convert 45 degree to expected direction")
    @Test
    void shouldConvert45DegreeToExpectedDirection() {
        var actualDirection = converter.convert(45);
        assertThat(actualDirection).isEqualTo("NE");
    }

    @DisplayName("Should convert 90 degree to expected direction")
    @Test
    void shouldConvert90DegreeToExpectedDirection() {
        var actualDirection = converter.convert(90);
        assertThat(actualDirection).isEqualTo("E");
    }

    @DisplayName("Should convert 135 degree to expected direction")
    @Test
    void shouldConvert135DegreeToExpectedDirection() {
        var actualDirection = converter.convert(135);
        assertThat(actualDirection).isEqualTo("SE");
    }

    @DisplayName("Should convert 180 degree to expected direction")
    @Test
    void shouldConvert180DegreeToExpectedDirection() {
        var actualDirection = converter.convert(180);
        assertThat(actualDirection).isEqualTo("S");
    }

    //225, 270, 315, 360, ...
}