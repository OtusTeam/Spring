package ru.otus.services;

import ru.otus.config.ApplicationStopServiceSettingsProvider;
import ru.otus.services.processors.InputService;

import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationStopServiceImpl implements ApplicationStopService {

    private final InputService inputService;
    private final ApplicationStopServiceSettingsProvider settingsProvider;
    private final AtomicBoolean executionFlag;

    public ApplicationStopServiceImpl(InputService inputService,
                                      ApplicationStopServiceSettingsProvider settingsProvider) {
        this.inputService = inputService;
        this.settingsProvider = settingsProvider;
        this.executionFlag = new AtomicBoolean(true);
    }

    @Override
    public boolean isApplicationRunning() {
        return executionFlag.get();
    }

    @Override
    public void stopApplication() {
        if (settingsProvider.isConfirmExit()) {
            var exitConfirmation = inputService.readStringWithPrompt("Действительно выйти? (да/нет)");
            if (exitConfirmation.equalsIgnoreCase("нет")) {
                return;
            }
        }
        executionFlag.set(false);
    }
}
