package ru.otus.services.processors;

import ru.otus.services.menu.MenuOption;

public interface MenuSingleCommandProcessor {
    void processCommand();
    MenuOption getProcessedCommandOption();
}
