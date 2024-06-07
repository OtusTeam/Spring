package ru.otus.services.processors;

import ru.otus.services.menu.MenuOption;

public interface MenuCommandsProcessor {
    void processMenuCommand(MenuOption selectedMenuOption);
}
