package ru.otus.services.processors;

import ru.otus.exceptions.MenuCommandProcessorNotFound;
import ru.otus.services.menu.MenuOption;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MenuCommandsProcessorImpl implements MenuCommandsProcessor {
    private final Map<MenuOption, MenuSingleCommandProcessor> processors;

    public MenuCommandsProcessorImpl(List<MenuSingleCommandProcessor> processors) {
        this.processors = processors.stream()
                .collect(Collectors.toMap(MenuSingleCommandProcessor::getProcessedCommandOption, Function.identity()));
    }

    @Override
    public void processMenuCommand(MenuOption selectedMenuOption) {
        var commandProcessor = processors.get(selectedMenuOption);
        if (commandProcessor == null) {
            throw new MenuCommandProcessorNotFound("Menu command processor for given option does not registered");
        }
        commandProcessor.processCommand();
    }
}
