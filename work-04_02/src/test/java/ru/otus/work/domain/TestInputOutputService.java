package ru.otus.work.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.work.service.ConsoleContext;
import ru.otus.work.service.InputOutputService;
import ru.otus.work.service.InputOutputServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест класса InputOutputService")
public class TestInputOutputService {

    private static final String MESSAGE = "message";

    private ByteArrayOutputStream byteArrayOutputStream;
    private InputOutputService inputOutputService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());

        byteArrayOutputStream = new ByteArrayOutputStream();
        ConsoleContext consoleContext = new ConsoleContext(new PrintStream(byteArrayOutputStream), System.in);
        inputOutputService = new InputOutputServiceImpl(consoleContext);
    }

    @Test
    public void inputOutput() {
        inputOutputService.printMessage(MESSAGE);
        assertThat(byteArrayOutputStream.toString()).isEqualTo(MESSAGE + "\r\n");
    }
}
