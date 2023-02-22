package ru.otus.ioservice.example.console;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.ioservice.example.api.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@ConditionalOnProperty(name = "use.console", havingValue = "true")
@Service
public class IOServiceStreams implements IOService {
    private final PrintStream out;
    private final Scanner sc;


    public IOServiceStreams(@Value("#{T(java.lang.System).out}") PrintStream out,
                            @Value("#{T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }
}
