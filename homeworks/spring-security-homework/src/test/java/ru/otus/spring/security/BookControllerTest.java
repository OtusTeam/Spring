package ru.otus.spring.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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

    @DisplayName("Тестирование GET запросов")
    @WithMockUser(
            username = "testUser"
    )
    @ParameterizedTest
    @ValueSource(strings = {"/api/books/", "/api/books/1", "/api/books/1/comments", "/api/books/1/authors", "/api/books/1/genres"})
    void testAuthenticatedGETRequests(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk());

    }

    @DisplayName("Тестирование POST запросов")
    @WithMockUser(
            username = "testUser"
    )
    @ParameterizedTest
    @ValueSource(strings = {"/api/books/"})
    void testAuthenticatedPOSTRequest(String url) throws Exception {
        mockMvc.perform(post(url)
                        .content("{\"name\": \"testBook\",\"commentText\": \"testCommentText\", \"authorName\": \"testAuthorName\", \"genreName\": \"testGenreName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Тестирование PUT запросов")
    @WithMockUser(
            username = "testUser"
    )
    @ParameterizedTest
    @ValueSource(strings = {"/api/books/1"})
    void testAuthenticatedPUTRequest(String url) throws Exception {
        mockMvc.perform(put(url)
                        .content("{\"name\": \"testBookName\"}").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @DisplayName("Тестирование DELETE запросов")
    @WithMockUser(
            username = "testUser"
    )
    @ParameterizedTest
    @ValueSource(strings = {"/api/books/1"})
    void testAuthenticatedDELETERequest(String url) throws Exception {
        mockMvc.perform(delete(url))
                .andExpect(status().isOk());

    }
}
