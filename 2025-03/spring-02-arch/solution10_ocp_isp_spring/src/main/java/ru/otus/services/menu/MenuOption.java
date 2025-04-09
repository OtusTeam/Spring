package ru.otus.services.menu;

public class MenuOption {
    private final int id;

    private final String description;

    public MenuOption(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuOption that = (MenuOption) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
