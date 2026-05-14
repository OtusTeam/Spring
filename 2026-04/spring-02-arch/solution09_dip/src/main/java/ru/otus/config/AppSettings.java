package ru.otus.config;

public class AppSettings {
    private final boolean confirmExit;
    private final String dateTimeFormat;

    public AppSettings(boolean confirmExit, String dateTimeFormat) {
        this.confirmExit = confirmExit;
        this.dateTimeFormat = dateTimeFormat;
    }

    public boolean isConfirmExit() {
        return confirmExit;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
