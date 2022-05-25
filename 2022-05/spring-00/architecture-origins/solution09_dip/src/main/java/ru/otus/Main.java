package ru.otus;

import ru.otus.config.AppSettings;
import ru.otus.services.*;

// +AppSettings, +ApplicationStopService, IOService стал Streams, методы MenuCommandsProcessor очистились от IOService
public class Main {
    public static void main(String[] args) {
        var appSettings = new AppSettings(true, "dd.mm.YYYY HH:mm:ss");
        var ioService = new IOServiceStreams(System.out, System.in);
        var applicationStopService = new ApplicationStopServiceImpl(ioService, appSettings);
        var notesService = new NotesServiceImpl();
        var noteConverter = new NoteConverterImpl(appSettings);
        var menuCommandsProcessor = new MenuCommandsProcessorImpl(ioService, notesService,
                noteConverter, applicationStopService);

        new ApplicationRunner(ioService, applicationStopService, menuCommandsProcessor)
                .run();
    }
}
