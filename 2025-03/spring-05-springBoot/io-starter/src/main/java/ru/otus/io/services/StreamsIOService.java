package ru.otus.io.services;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StreamsIOService implements IOService {

    private final PrintStream ps;
    private final Scanner sc;

    public StreamsIOService(PrintStream ps, InputStream is) {
        this.ps = ps;
        this.sc = new Scanner(is);
    }

    @Override
    public void outputString(String s) {
        ps.println(s);
    }

    @Override
    public void outputAsString(Object o) {
        ps.println(o);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }
}
