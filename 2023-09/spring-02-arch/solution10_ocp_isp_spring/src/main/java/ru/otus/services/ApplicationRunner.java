package ru.otus.services;

import ru.otus.exceptions.MenuCommandProcessorNotFound;
import ru.otus.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.exceptions.NoteIndexOutOfBoundsException;
import ru.otus.services.menu.MenuOption;
import ru.otus.services.menu.MenuOptionsRegistry;
import ru.otus.services.processors.MenuCommandsProcessor;

import java.util.Comparator;

public class ApplicationRunner {
    private final IOService ioService;

    private final ApplicationStopService applicationStopService;

    private final MenuOptionsRegistry menuOptionsRegistry;

    private final MenuCommandsProcessor commandsProcessor;



    public ApplicationRunner(IOService ioService,
                             ApplicationStopService applicationStopService,
                             MenuOptionsRegistry menuOptionsRegistry,
                             MenuCommandsProcessor commandsProcessor) {
        this.ioService = ioService;
        this.applicationStopService = applicationStopService;
        this.menuOptionsRegistry = menuOptionsRegistry;
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
            } catch (MenuCommandProcessorNotFound e) {
                ioService.outputString("Не найден обработчик для выбранного пункта меню");
            }
        }
    }

    private void outputMenu() {
        ioService.outputString("Выберите одно из следующих действий...");
        menuOptionsRegistry.getAvailableMenuOptions().stream()
                .sorted(Comparator.comparingInt(MenuOption::getId))
                .map(m -> m.getId() + ". " + m.getDescription())
                .forEach(ioService::outputString);
    }

    private void processMenuCommand(int selectedMenuItemId) {
        var selectedMenuOption = menuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
                .orElseThrow(() -> new MenuItemIndexOutOfBoundsException("Given menu item index is out of range"));

        commandsProcessor.processMenuCommand(selectedMenuOption);
    }

    private int readSelectedOptionNumber() {
        return ioService.readInt();
    }
}
