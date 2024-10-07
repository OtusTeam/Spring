package ru.otus.testingdemo.converter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WindDirectionConverterEmptyUnitTest {

    public WindDirectionConverterEmptyUnitTest() {
        System.out.printf("%nConstructor. HashCode: %s%n", Integer.toHexString(hashCode()));
    }

    @BeforeAll
    static void globalSetUp() {
        System.out.println("GlobalSetUp");
        //throw new RuntimeException("Ooops in setUp");
    }

    @AfterAll
    static void globalTearDown() {
        System.out.println("GlobalTearDown");
        //throw new RuntimeException("Ooops in tearDown");
    }

    @BeforeEach
    void setUp() {
        System.out.printf("SetUp. HashCode: %s%n", Integer.toHexString(hashCode()));
        //throw new RuntimeException("Ooops in setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.printf("TearDown. HashCode: %s%n%n", Integer.toHexString(hashCode()));
        //throw new RuntimeException("Ooops in tearDown");
    }

    @Test
    void shouldConvertDegreeToDirection1() {
        System.out.printf("ShouldConvertDegreeToDirection1. HashCode: %s%n", Integer.toHexString(hashCode()));
        //throw new RuntimeException("Ooops in shouldConvertDegreeToDirection1");
    }

    @Test
    void shouldConvertDegreeToDirection2() {
        System.out.printf("ShouldConvertDegreeToDirection2. HashCode: %s%n", Integer.toHexString(hashCode()));
        //throw new RuntimeException("Ooops in shouldConvertDegreeToDirection2");
    }
}