package ru.otus.example.applicationeventsdemo.shell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.InputProvider;
import org.springframework.shell.ResultHandlerService;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.example.applicationeventsdemo.events.EventsPublisher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

@DisplayName("Тест команд shell ")
@SpringBootTest
class ApplicationEventsCommandsTest {

    private static final String GREETING_PATTERN = "Добро пожаловать: %s";
    private static final String DEFAULT_LOGIN = "AnyUser";
    private static final String CUSTOM_LOGIN = "Вася";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGIN_SHORT = "l";
    private static final String COMMAND_PUBLISH = "publish";
    private static final String COMMAND_PUBLISH_EXPECTED_RESULT = "Событие опубликовано";
    private static final String COMMAND_LOGIN_PATTERN = "%s %s";

    private InputProvider inputProvider;

    private ArgumentCaptor<Object> argumentCaptor;

    @MockBean
    private EventsPublisher eventsPublisher;


    @SpyBean
    private ResultHandlerService resultHandlerService;

    @Autowired
    private Shell shell;

    @BeforeEach
    void setUp() {
        inputProvider = mock(InputProvider.class);
        argumentCaptor = ArgumentCaptor.forClass(Object.class);
    }

    @DisplayName(" должен возвращать приветствие для всех форм команды логина")
    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_LOGIN)
                .thenReturn(() -> COMMAND_LOGIN_SHORT)
                .thenReturn(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_LOGIN_SHORT, CUSTOM_LOGIN))
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(3)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).containsExactlyInAnyOrder(String.format(GREETING_PATTERN, DEFAULT_LOGIN),
                String.format(GREETING_PATTERN, DEFAULT_LOGIN),
                String.format(GREETING_PATTERN, CUSTOM_LOGIN));
    }

    @DisplayName(" должен возвращать CommandNotCurrentlyAvailable если при попытке выполнения команды publish пользователь выполнил вход")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnCommandNotCurrentlyAvailableObjectWhenUserDoesNotLoginAfterPublishCommandEvaluated() {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_PUBLISH)
                .thenReturn(null);

        assertThatCode(() -> shell.run(inputProvider)).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

    @DisplayName(" должен возвращать статус выполнения команды publish и вызвать соответствующий метод сервиса есл и команда выполнена после входа")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnExpectedMessageAndFirePublishMethodAfterPublishCommandEvaluated() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_LOGIN)
                .thenReturn(() -> COMMAND_PUBLISH)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(2)).handle(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(COMMAND_PUBLISH_EXPECTED_RESULT);
        verify(eventsPublisher, times(1)).publish();
    }
}