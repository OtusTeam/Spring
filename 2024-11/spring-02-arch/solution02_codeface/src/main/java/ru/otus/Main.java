package ru.otus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

// Имена переменных, константы, выпрямление кода
public class Main {

    private static final int MENU_OPTION_SHOW_ALL_NOTES = 1;
    private static final int MENU_OPTION_ADD_NEW_NOTE = 2;
    private static final int MENU_OPTION_UPDATE_NOTE = 3;
    private static final int MENU_OPTION_DELETE_NOTE = 4;
    private static final int MENU_OPTION_EXIT = 5;

    public static void main(String[] args) {
        var notes = new ArrayList<String>();
        var userInput = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите одно из следующих действий...");
            System.out.println("1. Вывести все заметки");
            System.out.println("2. Добавить заметку");
            System.out.println("3. Изменить заметку");
            System.out.println("4. Удалить заметку");
            System.out.println("5. Выйти");

            try {
                var selectedOptionNumber = Integer.parseInt(userInput.nextLine());

                if (selectedOptionNumber == MENU_OPTION_SHOW_ALL_NOTES) {
                    System.out.println("Заметки:");
                    IntStream.range(1, notes.size() + 1)
                            .mapToObj(k -> k + " | " + notes.get(k - 1))
                            .forEach(System.out::println);
                    System.out.println();
                } else if (selectedOptionNumber == MENU_OPTION_ADD_NEW_NOTE) {
                    System.out.println("Введите текст заметки...");
                    var noteText = userInput.nextLine();
                    var finalNoteText = LocalDateTime.now() + " | " + noteText;
                    notes.add(finalNoteText);
                } else if (selectedOptionNumber == MENU_OPTION_UPDATE_NOTE) {
                    System.out.println("Введите номер изменяемой заметки...");

                    var updatedNoteNumber = Integer.parseInt(userInput.nextLine());

                    if (updatedNoteNumber <= 0 || updatedNoteNumber > notes.size()) {
                        System.out.println("Введен несуществующий номер заметки");
                        continue;
                    }

                    System.out.println("Введите текст заметки...");
                    var noteText = userInput.nextLine();
                    var finalNoteText = LocalDateTime.now() + " | " + noteText;
                    notes.set(updatedNoteNumber - 1, finalNoteText);
                } else if (selectedOptionNumber == MENU_OPTION_DELETE_NOTE) {
                    System.out.println("Введите номер удаляемой заметки...");

                    var deletedNoteNumber = Integer.parseInt(userInput.nextLine());
                    if (deletedNoteNumber <= 0 || deletedNoteNumber > notes.size()) {
                        System.out.println("Введен несуществующий номер заметки");
                        continue;
                    }

                    notes.remove(deletedNoteNumber - 1);
                } else if (selectedOptionNumber == MENU_OPTION_EXIT) {
                    break;
                } else {
                    System.out.println("Введен неверный номер опции");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка при вводе числа");
            }
        }
    }
}
