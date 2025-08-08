package ru.otus.services.menu;

import java.util.List;
import java.util.Optional;

public interface MenuOptionsRegistry {
    List<MenuOption> getAvailableMenuOptions();
    Optional<MenuOption> getMenuOptionById(int id);
}
