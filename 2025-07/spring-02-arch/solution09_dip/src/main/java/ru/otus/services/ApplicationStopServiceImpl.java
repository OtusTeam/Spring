package ru.otus.services;

import ru.otus.config.AppSettings;

import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationStopServiceImpl implements ApplicationStopService {

    private final IOService ioService;
    private final AppSettings appSettings;
    private final AtomicBoolean executionFlag;

    public ApplicationStopServiceImpl(IOService ioService, AppSettings appSettings) {
        this.ioService = ioService;
        this.appSettings = appSettings;
        this.executionFlag = new AtomicBoolean(true);
    }

    @Override
    public boolean isApplicationRunning() {
        return executionFlag.get();
    }

    @Override
    public void stopApplication() {
        if (appSettings.isConfirmExit()) {
            var exitConfirmation = ioService.readStringWithPrompt("Действительно выйти? (да/нет)");
            if (exitConfirmation.equalsIgnoreCase("нет")) {
                return;
            }
        }
        executionFlag.set(false);
    }
}
