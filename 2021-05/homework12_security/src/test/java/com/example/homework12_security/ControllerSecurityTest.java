package com.example.homework12_security;

import com.example.homework12_security.controller.WordController;
import com.example.homework12_security.repository.DictionaryRepository;
import com.example.homework12_security.repository.WordRepository;
import com.example.homework12_security.security.UserDetailSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ControllerSecurityTest.class)
@Import(WordController.class)
public class ControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserDetailSecurityService userService;

    @MockBean
    WordRepository repository;

    @MockBean
    DictionaryRepository dictionaryRepository;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testWordsAllOnAdmin() throws Exception {
        mockMvc.perform(get("/words/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testWordsAllOnAdminRedirectLogin() throws Exception {
        mockMvc.perform(get("/words/all"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testEditWordOnAdmin() throws Exception {
        mockMvc.perform(get("/words/edit").param("id", "1"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testAddWordOnAdmin() throws Exception {
        mockMvc.perform(get("/words/addWord").param("id", "1"))
                .andExpect(status().isOk());
    }

}
