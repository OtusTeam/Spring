package ru.otus.config;

public class AppSettings implements DateTimeFormatProvider, ApplicationStopServiceSettingsProvider{
    private final boolean confirmExit;
    private final String dateTimeFormat;

    public AppSettings(boolean confirmExit, String dateTimeFormat) {
        this.confirmExit = confirmExit;
        this.dateTimeFormat = dateTimeFormat;
    }

    @Override
    public boolean isConfirmExit() {
        return confirmExit;
    }

    @Override
    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
