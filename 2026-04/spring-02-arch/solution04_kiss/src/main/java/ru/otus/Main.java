package ru.otus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

// Уменьшение вложенности в main за счет выноса обработки команд в метод + executionFlag
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
                System.out.println("Ошибка при вводе числа");
            }
        }
    }

    private static void outputMenu(){
        System.out.println("Выберите одно из следующих действий...");
        System.out.println("1. Вывести все заметки");
        System.out.println("2. Добавить заметку");
        System.out.println("3. Изменить заметку");
        System.out.println("4. Удалить заметку");
        System.out.println("5. Выйти");
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
            System.out.println("Введен неверный номер опции");
        }
    }

    private static int readSelectedOptionNumberFrom(Scanner userInput) {
        return Integer.parseInt(userInput.nextLine());
    }

    private static void showAllNotes(List<String> notes) {
        System.out.println("Заметки:");
        IntStream.range(1, notes.size() + 1)
                .mapToObj(k -> k + " | " + notes.get(k - 1))
                .forEach(System.out::println);
        System.out.println();
    }

    private static void addNewNote(Scanner userInput, List<String> notes) {
        System.out.println("Введите текст заметки...");
        var noteText = userInput.nextLine();
        var finalNoteText = LocalDateTime.now() + " | " + noteText;
        notes.add(finalNoteText);
    }

    private static void updateNote(Scanner userInput, List<String> notes) {
        System.out.println("Введите номер изменяемой заметки...");

        var updatedNoteNumber = Integer.parseInt(userInput.nextLine());
        if (updatedNoteNumber <= 0 || updatedNoteNumber > notes.size()) {
            System.out.println("Введен несуществующий номер заметки");
            return;
        }

        System.out.println("Введите текст заметки...");
        var noteText = userInput.nextLine();
        var finalNoteText = LocalDateTime.now() + " | " + noteText;
        notes.set(updatedNoteNumber - 1, finalNoteText);
    }

    private static void deleteNote(Scanner userInput, List<String> notes) {
        System.out.println("Введите номер удаляемой заметки...");

        var deletedNoteNumber = Integer.parseInt(userInput.nextLine());
        if (deletedNoteNumber <= 0 || deletedNoteNumber > notes.size()) {
            System.out.println("Введен несуществующий номер заметки");
            return;
        }

        notes.remove(deletedNoteNumber - 1);
    }
}
