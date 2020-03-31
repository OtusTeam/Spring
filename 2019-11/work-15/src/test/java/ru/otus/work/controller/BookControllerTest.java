package ru.otus.work.controller;

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
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;
import ru.otus.work.service.BookService;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(NAME)));
    }

    @Test
    @DisplayName("Вернуть книгу по идентификатору")
    public void getById() throws Exception {
        mockMvc.perform(get("/books")
                .param("id", ID.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(NAME)));
    }

    @Test
    @DisplayName("Сохранение книги")
    public void saveBook() throws Exception {
        mockMvc.perform(post("/books")
                .param("id", ID.toString())
                .param("name", NAME)
                .param("author", AUTHOR)
                .param("genre", GENRE)
                .param("description", DESCRIPTION)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Удаление книги")
    public void deleteBook() throws Exception {
        mockMvc.perform(get("/deletebooks")
                .param("id", ID.toString()))
                .andExpect(status().isOk());
    }
}
