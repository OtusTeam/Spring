package ru.otus.smigunov.project.controller;

import lombok.SneakyThrows;
import org.hibernate.annotations.Type;
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
import ru.otus.smigunov.project.domain.User;
import ru.otus.smigunov.project.service.EmailService;

import javax.persistence.Column;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class UserControllerTest {

    private String ID = "1";
    private String NAME = "admin";
    private String USERNAME = "USERNAME";
    private String DESCRIPTION = "DESCRIPTION";
    private String AUTHORITY = "AUTHORITY";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmailService emailService;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        doNothing().when(emailService).sendEmail(null, null);
    }

    @Test
    @DisplayName("Вернуть всех пользователей")
    public void getAll() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(NAME)));
    }

    @Test
    @DisplayName("Вернуть пользователя по идентификатору")
    public void getById() throws Exception {
        mockMvc.perform(get("/users")
                .param("id", ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(NAME)));
    }

    @Test
    @DisplayName("Сохранение пользователя")
    public void save() throws Exception {
        mockMvc.perform(post("/userSave")
                .param("name", NAME)
                .param("username", USERNAME)
                .param("description", DESCRIPTION)
                .param("authority", AUTHORITY)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }
}
