package ru.otus.smigunov.project.controller;

import lombok.SneakyThrows;
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
import ru.otus.smigunov.project.service.EmailService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class CheckListControllerTest {

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
    @DisplayName("Сохранение листа и проверка на существование")
    public void save() throws Exception {

        String userid = "10";
        String daynumber = "1";
        String othersymptoms = "othersymptoms";

        mockMvc.perform(post("/checkListSave")
                .param("userid", userid)
                .param("daynumber", daynumber)
                .param("nonsymptoms", "true")
                .param("fever", "true")
                .param("sorethroat", "true")
                .param("cough", "true")
                .param("runnynose", "true")
                .param("dyspnea", "true")
                .param("othersymptoms", othersymptoms)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());

        mockMvc.perform(get("/checkLists")
                .param("userid", userid))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(othersymptoms)));

    }
}
