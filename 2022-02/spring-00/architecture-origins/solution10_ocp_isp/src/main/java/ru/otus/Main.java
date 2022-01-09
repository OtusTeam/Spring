package ru.otus;

import ru.otus.config.AppSettings;
import ru.otus.services.*;
import ru.otus.services.processors.*;
import ru.otus.services.menu.MenuOption;
import ru.otus.services.menu.MenuOptionsRegistryImpl;


import java.util.List;

// OCP + ISP (AppSettings + IOService) + тесты
public class Main {
    public static void main(String[] args) {
        var appSettings = new AppSettings(true, "dd.mm.YYYY HH:mm:ss");
        var ioService = new IOServiceStreams(System.out, System.in);
        var applicationStopService = new ApplicationStopServiceImpl(ioService, appSettings);
        var notesService = new NotesServiceImpl();
        var noteConverter = new NoteConverterImpl(appSettings);

        var menuOptions = List.of(
                new MenuOption(1, "Вывести все заметки"),
                new MenuOption(2, "Добавить заметку"),
                new MenuOption(3, "Изменить заметку"),
                new MenuOption(4, "Удалить заметку"),
                new MenuOption(5, "Выйти")

        );
        var menuOptionsRegistry = new MenuOptionsRegistryImpl(menuOptions);

        var processors = List.of(
                new ShowAllNotesSingleCommandProcessor(ioService, notesService, noteConverter, menuOptions.get(0)),
                new AddNewNoteSingleCommandProcessor(ioService, notesService, menuOptions.get(1)),
                new UpdateNoteSingleCommandProcessor(ioService, notesService, menuOptions.get(2)),
                new DeleteNoteSingleCommandProcessor(ioService, notesService, menuOptions.get(3)),
                new StopApplicationSingleCommandProcessor(applicationStopService, menuOptions.get(4))
        );

        var menuCommandsProcessor = new MenuCommandsProcessorImpl(processors);

        new ApplicationRunner(ioService, applicationStopService, menuOptionsRegistry, menuCommandsProcessor)
                .run();
    }
}
