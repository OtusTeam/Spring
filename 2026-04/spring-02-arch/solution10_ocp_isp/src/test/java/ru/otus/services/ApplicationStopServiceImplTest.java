package ru.otus.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.config.ApplicationStopServiceSettingsProvider;
import ru.otus.services.processors.InputService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис остановки приложения ")
class ApplicationStopServiceImplTest {

    @DisplayName("должен возвращать корректный статус работы приложения")
    @ParameterizedTest(name = "остановить приложение: {0}, ожидаемый результат: {1}")
    @CsvSource({"false, true", "true, false"})
    void shouldReturnCorrectApplicationExecutionStatus(boolean shouldStopApplication, boolean expectedStatus) {
        var inputService = mock(InputService.class);
        var settingsProvider = mock(ApplicationStopServiceSettingsProvider.class);
        given(settingsProvider.isConfirmExit()).willReturn(false);
        var applicationStopService = new ApplicationStopServiceImpl(inputService, settingsProvider);

        if (shouldStopApplication) {
            applicationStopService.stopApplication();
            verify(settingsProvider, times(1)).isConfirmExit();
        }
        var actualStatus = applicationStopService.isApplicationRunning();
        assertThat(actualStatus).isEqualTo(expectedStatus);
        verify(inputService, never()).readStringWithPrompt(any());
    }
}