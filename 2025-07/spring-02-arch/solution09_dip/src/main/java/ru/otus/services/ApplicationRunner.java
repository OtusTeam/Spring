package ru.otus.services;

import ru.otus.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.exceptions.NoteIndexOutOfBoundsException;

public class ApplicationRunner {
    private static final int MENU_OPTION_SHOW_ALL_NOTES = 1;
    private static final int MENU_OPTION_ADD_NEW_NOTE = 2;
    private static final int MENU_OPTION_UPDATE_NOTE = 3;
    private static final int MENU_OPTION_DELETE_NOTE = 4;
    private static final int MENU_OPTION_EXIT = 5;

    private final IOService ioService;
    private final ApplicationStopService applicationStopService;
    private final MenuCommandsProcessor commandsProcessor;


    public ApplicationRunner(IOService ioService,
                             ApplicationStopService applicationStopService,
                             MenuCommandsProcessor commandsProcessor) {
        this.ioService = ioService;
        this.applicationStopService = applicationStopService;
        this.commandsProcessor = commandsProcessor;
    }

    public void run() {
        while (applicationStopService.isApplicationRunning()) {
            outputMenu();
            try {
                var selectedMenuItem = readSelectedOptionNumber();
                processMenuCommand(selectedMenuItem);

            } catch (NumberFormatException e) {
                ioService.outputString("Ошибка при вводе числа");
            } catch (MenuItemIndexOutOfBoundsException e) {
                ioService.outputString("Введен неверный номер опции");
            } catch (NoteIndexOutOfBoundsException e) {
                ioService.outputString("Введен несуществующий номер заметки");
            }
        }
    }

    private void outputMenu(){
        ioService.outputString("Выберите одно из следующих действий...");
        ioService.outputString("1. Вывести все заметки");
        ioService.outputString("2. Добавить заметку");
        ioService.outputString("3. Изменить заметку");
        ioService.outputString("4. Удалить заметку");
        ioService.outputString("5. Выйти");
    }

    private void processMenuCommand(int selectedMenuItemIndex) {
        if (selectedMenuItemIndex == MENU_OPTION_SHOW_ALL_NOTES) {
            commandsProcessor.showAllNotes();
        } else if (selectedMenuItemIndex == MENU_OPTION_ADD_NEW_NOTE) {
            commandsProcessor.addNewNote();
        } else if (selectedMenuItemIndex == MENU_OPTION_UPDATE_NOTE) {
            commandsProcessor.updateNote();
        } else if (selectedMenuItemIndex == MENU_OPTION_DELETE_NOTE) {
            commandsProcessor.deleteNote();
        } else if (selectedMenuItemIndex == MENU_OPTION_EXIT){
            commandsProcessor.stopApplication();
        } else {
            throw new MenuItemIndexOutOfBoundsException("Given menu item index is out of range");
        }
    }

    private int readSelectedOptionNumber() {
        return ioService.readInt();
    }
}
