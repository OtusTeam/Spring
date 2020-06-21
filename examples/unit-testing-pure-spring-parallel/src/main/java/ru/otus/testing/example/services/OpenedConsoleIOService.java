package ru.otus.testing.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class OpenedConsoleIOService implements IOService {
    private final PrintStream out;
    private final Scanner sc;


    public OpenedConsoleIOService(@Value("#{ T(java.lang.System).in}") InputStream in,
                                  @Value("#{ T(java.lang.System).out}") PrintStream out) {
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
