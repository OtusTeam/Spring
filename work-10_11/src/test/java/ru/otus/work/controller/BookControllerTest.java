package ru.otus.work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.work.controller.dto.BookDto;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;
import ru.otus.work.service.BookService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    private final Long ID = 1L;
    private final String NAME = "name";
    private final String AUTHOR = "author";
    private final String GENRE = "genre";
    private final String DESCRIPTION = "description";

    public Author author = Author.builder()
            .id(ID).name(AUTHOR)
            .build();

    public Genre genre = Genre.builder()
            .id(ID).name(GENRE)
            .build();

    private Book book = Book
            .builder()
            .id(ID)
            .name(NAME)
            .description(DESCRIPTION)
            .author(author)
            .genre(genre)
            .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @BeforeEach
    public void setUp() {
        given(bookService.listAll()).willReturn(Collections.singletonList(book));
        given(bookService.findById(eq(ID))).willReturn(book);
        doNothing().when(bookService).delete(ID);
        doNothing().when(bookService).save(
                eq(NAME),
                eq(AUTHOR),
                eq(GENRE),
                eq(DESCRIPTION)
        );
        doNothing().when(bookService).modify(
                eq(ID.toString()),
                eq(NAME),
                eq(AUTHOR),
                eq(GENRE),
                eq(DESCRIPTION)
        );
    }

    @Test
    @DisplayName("Вернуть все книги")
    public void getAll() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].name").value(NAME))
                .andExpect(jsonPath("$[0].author").value(AUTHOR))
                .andExpect(jsonPath("$[0].genre").value(GENRE))
                .andExpect(jsonPath("$[0].description").value(DESCRIPTION));
    }

    @Test
    @DisplayName("Вернуть книгу по идентификатору")
    public void getById() throws Exception {
        mockMvc.perform(get("/api/books/{id}", ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.author").value(AUTHOR))
                .andExpect(jsonPath("$.genre").value(GENRE))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    @DisplayName("Сохранение книги")
    public void saveBook() throws Exception {
        mockMvc.perform(post("/api/books")
                .content(new ObjectMapper().writeValueAsString(BookDto.toDto(book)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Изменение книги")
    public void modifyBook() throws Exception {
        mockMvc.perform(put("/api/books")
                .content(new ObjectMapper().writeValueAsString(BookDto.toDto(book)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Удаление книги")
    public void deleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/{id}", ID))
                .andExpect(status().isOk());
    }
}
