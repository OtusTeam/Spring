package ru.otus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите одно из следующих действий...");
            System.out.println("1. Вывести все заметки");
            System.out.println("2. Добавить заметку");
            System.out.println("3. Изменить заметку");
            System.out.println("4. Удалить заметку");
            System.out.println("5. Выйти");

            try {
                int i = Integer.parseInt(sc.nextLine());

                if (i == 1) {
                    System.out.println("Заметки:");
                    IntStream.range(1, strings.size() + 1)
                            .mapToObj(k -> k + " | " + strings.get(k - 1))
                            .forEach(System.out::println);
                    System.out.println();
                } else if (i == 2) {
                    System.out.println("Введите текст заметки...");
                    String s = sc.nextLine();
                    strings.add(LocalDateTime.now() + " | " + s);
                } else if (i == 3) {
                    System.out.println("Введите номер изменяемой заметки...");
                    int k = Integer.parseInt(sc.nextLine());

                    if (k > 0 && k <= strings.size()) {
                        System.out.println("Введите текст заметки...");
                        String s = sc.nextLine();
                        strings.set(k - 1, LocalDateTime.now() + " | " + s);
                    } else {
                        System.out.println("Введен несуществующий номер заметки");
                    }
                } else if (i == 4) {
                    System.out.println("Введите номер удаляемой заметки...");
                    int k = Integer.parseInt(sc.nextLine());
                    if (k > 0 && k <= strings.size()) {
                        strings.remove(k - 1);
                    } else {
                        System.out.println("Введен несуществующий номер заметки");
                    }
                } else if (i == 5) {
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
