package ru.otus.testing.example.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.testing.example.TestingExampleSpringApplication;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestingExampleSpringApplication.class)
@DisplayName("Тест ConsoleIOService")
class ConsoleIOServiceTest {

    private static final String TEXT_TO_PRINT1 = "Ничто не истинно";
    private static final String TEXT_TO_PRINT2 = "Все дозволено";

    private PrintStream backup;
    private ByteArrayOutputStream bos;

    @Autowired
    private ConsoleIOService ioService;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        backup = System.out;
        System.setOut(new PrintStream(bos));
    }

    @AfterEach
    void tearDown() {
        System.setOut(backup);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT1 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintOnlyFirstCreedLine() {
        ioService.out(TEXT_TO_PRINT1);
        Thread.sleep(100);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT1 + "\r\n");
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT2 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintOnlySecondCreedLine() {
        ioService.out(TEXT_TO_PRINT2);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT2 + "\r\n");
    }
}