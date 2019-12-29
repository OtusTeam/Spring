package ru.otus.work.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConsoleContext {
    private PrintStream out = System.out;
    private InputStream in = System.in;
}
