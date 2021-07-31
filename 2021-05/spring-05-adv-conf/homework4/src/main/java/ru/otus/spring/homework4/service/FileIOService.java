package ru.otus.spring.homework4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework4.util.FileManagerImpl;

import java.util.Scanner;

@Service
public class FileIOService implements IOService {
    private final Scanner sc;

    public FileIOService(@Autowired FileManagerImpl fm) {
        this.sc = new Scanner(fm.getFile());
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

    }

    @Override
    public boolean hasNext() {
        return sc.hasNextLine();
    }
}
