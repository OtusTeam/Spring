package ru.otus.services.processors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.model.Note;
import ru.otus.services.NotesService;
import ru.otus.services.menu.MenuOption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Процессор команды добавления новой заметки ")
@ExtendWith(MockitoExtension.class)
class AddNewNoteSingleCommandProcessorTest {

    @Mock
    private MenuOption processedCommandOption;

    @Mock
    private InputService inputService;

    @Mock
    private NotesService notesService;

    @InjectMocks
    private AddNewNoteSingleCommandProcessor processor;

    @DisplayName("должен корректно добавлять новой заметки ")
    @Test
    void shouldCorrectAddExpectedNoteUseExpectedServicesMethods() {
        var expectedNoteText = "Expected Note Text";
        given(inputService.readStringWithPrompt(anyString()))
                .willReturn(expectedNoteText);

        processor.processCommand();

        verify(inputService, times(1)).readStringWithPrompt(any());

        var captor = ArgumentCaptor.forClass(Note.class);
        verify(notesService).save(captor.capture());
        var actualNote = captor.getValue();
        assertThat(actualNote).extracting(Note::getText).isEqualTo(expectedNoteText);
    }

    @DisplayName("должен возвращает ожидаемый тип обрабатываемой команды")
    @Test
    void shouldReturnExpectedProcessedCommandOption() {
        assertThat(processor.getProcessedCommandOption()).isEqualTo(processedCommandOption);
    }
}