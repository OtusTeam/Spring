package ru.otus.services;

import java.util.Scanner;

public class ConsoleIOService {
    private final Scanner userInput;

    public ConsoleIOService() {
        userInput = new Scanner(System.in);
    }

    public void outputString(String s){
        System.out.println(s);
    }

    public int readInt(){
        return Integer.parseInt(userInput.nextLine());
    }

    public int readIntWithPrompt(String prompt){
        outputString(prompt);
        return Integer.parseInt(userInput.nextLine());
    }

    public String readStringWithPrompt(String prompt){
        outputString(prompt);
        return userInput.nextLine();
    }
}
