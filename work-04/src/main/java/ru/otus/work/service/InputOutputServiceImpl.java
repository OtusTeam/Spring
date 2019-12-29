package ru.otus.work.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final PrintStream outputStream;
    private final InputStream inputStream;

    public InputOutputServiceImpl(ConsoleContext consoleContext) {
        this.outputStream = consoleContext.getOut();
        this.inputStream = consoleContext.getIn();
    }

    @Override
    public void printMessage(String message) {
        outputStream.println(message);
    }

    @Override
    public String readMessage() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }
}
