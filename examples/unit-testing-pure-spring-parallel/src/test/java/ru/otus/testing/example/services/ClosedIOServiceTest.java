package ru.otus.testing.example.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Тест ClosedIOService")
class ClosedIOServiceTest {

    private static final String TEXT_TO_PRINT1 = "Ничто не истинно";
    private static final String TEXT_TO_PRINT2 = "Все дозволено";

    private PrintStream backup;
    private ByteArrayOutputStream bos;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());
        backup = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ioService = new ClosedConsoleIOService();
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
        Thread.sleep(1000);
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