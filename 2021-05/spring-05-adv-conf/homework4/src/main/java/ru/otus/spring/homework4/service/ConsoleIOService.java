package ru.otus.spring.homework4.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService{

    private Scanner sc;
    private PrintStream out;

    public ConsoleIOService() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }

    @Override
    public int readInt() {
        return sc.nextInt();
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }
}
