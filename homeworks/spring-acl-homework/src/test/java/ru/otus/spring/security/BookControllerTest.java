package ru.otus.spring.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.spring.page.BookPageController;
import ru.otus.spring.rest.BookController;
import ru.otus.spring.service.impl.UserDetailsServiceImpl;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@DisplayName("Тестирование REST запросов")
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @MockBean
    private BookController bookController;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @DisplayName("Тестирование запросов для пользователя с ролью ADMIN")
    @WithMockUser(
            username = "ADMIN",
            password = "passw0rd",
            roles = "ADMIN"
    )
    @Test
    void testAuthenticatedRequestsForAdminRole() throws Exception {
        mockMvc.perform(get("/api/books/"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1/comments"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1/authors"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1/genres"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/books/")
                        .content("{\"name\": \"testBook\",\"commentText\": \"testCommentText\", \"authorName\": \"testAuthorName\", \"genreName\": \"testGenreName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(put("/api/books/1")
                        .content("{\"name\": \"testBookName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());

    }

    @DisplayName("Тестирование запросов для пользователя с ролью USER")
    @WithMockUser(
            username = "USER",
            password = "passw0rd",
            roles = "USER"
    )
    @Test
    void testAuthenticatedRequestsForUserRole() throws Exception {
        mockMvc.perform(get("/api/books/"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1/comments"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1/authors"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1/genres"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/books/")
                        .content("{\"name\": \"testBook\",\"commentText\": \"testCommentText\", \"authorName\": \"testAuthorName\", \"genreName\": \"testGenreName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
        mockMvc.perform(put("/api/books/1")
                        .content("{\"name\": \"testBookName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isForbidden());

    }

    @DisplayName("Тестирование запросов для пользователя с ролью GUEST")
    @WithMockUser(
            username = "GUEST",
            password = "passw0rd",
            roles = "GUEST"
    )
    @Test
    void testAuthenticatedRequestsForGuestRole() throws Exception {
        mockMvc.perform(get("/api/books/"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/api/books/1/comments"))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/api/books/1/authors"))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/api/books/1/genres"))
                .andExpect(status().isForbidden());
        mockMvc.perform(post("/api/books/")
                        .content("{\"name\": \"testBook\",\"commentText\": \"testCommentText\", \"authorName\": \"testAuthorName\", \"genreName\": \"testGenreName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
        mockMvc.perform(put("/api/books/1")
                        .content("{\"name\": \"testBookName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isForbidden());

    }
}
