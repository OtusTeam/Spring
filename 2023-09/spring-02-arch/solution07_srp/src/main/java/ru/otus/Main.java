package ru.otus;

import ru.otus.exceptions.NoteIndexOutOfBoundsException;
import ru.otus.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.model.Note;
import ru.otus.services.ConsoleIOService;
import ru.otus.services.NoteConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

// Note, ConsoleIOService, NoteConverter
public class Main {

    private static final int MENU_OPTION_SHOW_ALL_NOTES = 1;
    private static final int MENU_OPTION_ADD_NEW_NOTE = 2;
    private static final int MENU_OPTION_UPDATE_NOTE = 3;
    private static final int MENU_OPTION_DELETE_NOTE = 4;
    private static final int MENU_OPTION_EXIT = 5;

    public static void main(String[] args) {
        var notes = new ArrayList<Note>();
        var ioService = new ConsoleIOService();

        var executionFlag = new AtomicBoolean(true);
        while (executionFlag.get()) {
            outputMenu(ioService);
            try {
                var selectedMenuItem = readSelectedOptionNumberFrom(ioService);
                processMenuCommand(selectedMenuItem, executionFlag, ioService, notes);

            } catch (NumberFormatException e) {
                ioService.outputString("Ошибка при вводе числа");
            } catch (MenuItemIndexOutOfBoundsException e) {
                ioService.outputString("Введен неверный номер опции");
            } catch (NoteIndexOutOfBoundsException e) {
                ioService.outputString("Введен несуществующий номер заметки");
            }
        }
    }

    private static void outputMenu(ConsoleIOService ioService){
        ioService.outputString("Выберите одно из следующих действий...");
        ioService.outputString("1. Вывести все заметки");
        ioService.outputString("2. Добавить заметку");
        ioService.outputString("3. Изменить заметку");
        ioService.outputString("4. Удалить заметку");
        ioService.outputString("5. Выйти");
    }

    private static void processMenuCommand(int selectedMenuItemIndex, AtomicBoolean executionFlag,
                                           ConsoleIOService ioService, List<Note> notes) {
        if (selectedMenuItemIndex == MENU_OPTION_SHOW_ALL_NOTES) {
            showAllNotes(ioService, notes);
        } else if (selectedMenuItemIndex == MENU_OPTION_ADD_NEW_NOTE) {
            addNewNote(ioService, notes);
        } else if (selectedMenuItemIndex == MENU_OPTION_UPDATE_NOTE) {
            updateNote(ioService, notes);
        } else if (selectedMenuItemIndex == MENU_OPTION_DELETE_NOTE) {
            deleteNote(ioService, notes);
        } else if (selectedMenuItemIndex == MENU_OPTION_EXIT){
            executionFlag.set(false);
        } else {
            throw new MenuItemIndexOutOfBoundsException("Given menu item index is out of range");
        }
    }

    private static int readSelectedOptionNumberFrom(ConsoleIOService ioService) {
        return ioService.readInt();
    }

    private static void showAllNotes(ConsoleIOService ioService, List<Note> notes) {
        var noteConverter = new NoteConverter();
        ioService.outputString("Заметки:");
        IntStream.range(1, notes.size() + 1)
                .mapToObj(k -> noteConverter.convertNoteToString(k, notes.get(k - 1)))
                .forEach(ioService::outputString);
        ioService.outputString("");
    }

    private static void addNewNote(ConsoleIOService ioService, List<Note> notes) {
        var noteText = ioService.readStringWithPrompt("Введите текст заметки...");
        notes.add( Note.of(noteText));
    }

    private static void checkNoteNumber(int noteNumber, List<Note> notes) {
        if (noteNumber <= 0 || noteNumber > notes.size()) {
            throw new NoteIndexOutOfBoundsException("Given number of note is out of range");
        }
    }

    private static void updateNote(ConsoleIOService ioService, List<Note> notes) {
        var updatedNoteNumber = ioService.readIntWithPrompt("Введите номер изменяемой заметки...");
        checkNoteNumber(updatedNoteNumber, notes);

        var noteText = ioService.readStringWithPrompt("Введите текст заметки...");
        notes.set(updatedNoteNumber - 1, Note.of(noteText));
    }

    private static void deleteNote(ConsoleIOService ioService, List<Note> notes) {
        var deletedNoteNumber = ioService.readIntWithPrompt("Введите номер удаляемой заметки...");
        checkNoteNumber(deletedNoteNumber, notes);

        notes.remove(deletedNoteNumber - 1);
    }
}
