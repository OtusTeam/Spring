package ru.otus.services.processors;

import ru.otus.services.ApplicationStopService;
import ru.otus.services.menu.MenuOption;

public class StopApplicationSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final ApplicationStopService applicationStopService;

    public StopApplicationSingleCommandProcessor(ApplicationStopService applicationStopService,
                                                 MenuOption processedCommandOption) {
        this.applicationStopService = applicationStopService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        applicationStopService.stopApplication();
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}
