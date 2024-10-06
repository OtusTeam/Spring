package ru.otus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

// Вынос логики ввода-вывода
public class Main {

    private static final int MENU_OPTION_SHOW_ALL_NOTES = 1;
    private static final int MENU_OPTION_ADD_NEW_NOTE = 2;
    private static final int MENU_OPTION_UPDATE_NOTE = 3;
    private static final int MENU_OPTION_DELETE_NOTE = 4;
    private static final int MENU_OPTION_EXIT = 5;

    public static void main(String[] args) {
        var notes = new ArrayList<String>();
        var userInput = new Scanner(System.in);

        var executionFlag = new AtomicBoolean(true);
        while (executionFlag.get()) {
            outputMenu();
            try {
                var selectedMenuItem = readSelectedOptionNumberFrom(userInput);
                processMenuCommand(selectedMenuItem, executionFlag, userInput, notes);
            } catch (NumberFormatException e) {
                outputString("Ошибка при вводе числа");
            }
        }
    }

    private static void outputMenu(){
        outputString("Выберите одно из следующих действий...");
        outputString("1. Вывести все заметки");
        outputString("2. Добавить заметку");
        outputString("3. Изменить заметку");
        outputString("4. Удалить заметку");
        outputString("5. Выйти");
    }

    private static void processMenuCommand(int selectedMenuItem, AtomicBoolean executionFlag,
                                           Scanner userInput, List<String> notes) {
        if (selectedMenuItem == MENU_OPTION_SHOW_ALL_NOTES) {
            showAllNotes(notes);
        } else if (selectedMenuItem == MENU_OPTION_ADD_NEW_NOTE) {
            addNewNote(userInput, notes);
        } else if (selectedMenuItem == MENU_OPTION_UPDATE_NOTE) {
            updateNote(userInput, notes);
        } else if (selectedMenuItem == MENU_OPTION_DELETE_NOTE) {
            deleteNote(userInput, notes);
        } else if (selectedMenuItem == MENU_OPTION_EXIT){
            executionFlag.set(false);
        } else {
            outputString("Введен неверный номер опции");
        }
    }

    private static int readSelectedOptionNumberFrom(Scanner userInput) {
        return readInt(userInput);
    }

    private static void showAllNotes(List<String> notes) {
        outputString("Заметки:");
        IntStream.range(1, notes.size() + 1)
                .mapToObj(k -> k + " | " + notes.get(k - 1))
                .forEach(Main::outputString);
        outputString("");
    }

    private static void addNewNote(Scanner userInput, List<String> notes) {
        var noteText = readStringWithPrompt(userInput, "Введите текст заметки...");
        var finalNoteText = LocalDateTime.now() + " | " + noteText;
        notes.add(finalNoteText);
    }

    private static void updateNote(Scanner userInput, List<String> notes) {
        int updatedNoteNumber = readIntWithPrompt(userInput, "Введите номер изменяемой заметки...");
        if (updatedNoteNumber <= 0 || updatedNoteNumber > notes.size()) {
            outputString("Введен несуществующий номер заметки");
            return;
        }
        var noteText = readStringWithPrompt(userInput, "Введите текст заметки...");
        var finalNoteText = LocalDateTime.now() + " | " + noteText;
        notes.set(updatedNoteNumber - 1, finalNoteText);
    }

    private static void deleteNote(Scanner userInput, List<String> notes) {
        var deletedNoteNumber = readIntWithPrompt(userInput, "Введите номер удаляемой заметки...");
        if (deletedNoteNumber <= 0 || deletedNoteNumber > notes.size()) {
            outputString("Введен несуществующий номер заметки");
            return;
        }

        notes.remove(deletedNoteNumber - 1);
    }

    private static void outputString(String s){
        System.out.println(s);
    }

    private static int readInt(Scanner userInput){
        return Integer.parseInt(userInput.nextLine());
    }

    private static int readIntWithPrompt(Scanner userInput, String prompt){
        outputString(prompt);
        return Integer.parseInt(userInput.nextLine());
    }

    private static String readStringWithPrompt(Scanner userInput, String prompt){
        outputString(prompt);
        return userInput.nextLine();
    }
}
