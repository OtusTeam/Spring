package ru.otus.ioservice.example.console;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.ioservice.example.api.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@ConditionalOnProperty(name = "use.swing", havingValue = "false")
@Service
public class ConsoleIOService implements IOService {
    private final PrintStream out;
    private final Scanner sc;


    public ConsoleIOService() {
        this.out = System.out;
        this.sc = new Scanner(System.in);
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
