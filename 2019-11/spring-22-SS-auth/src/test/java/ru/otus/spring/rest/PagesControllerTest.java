package ru.otus.spring.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PagesController.class)
public class PagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testAuthenticatedOnAdmin() throws Exception {
        mockMvc.perform(get("/authenticated"))
                .andExpect(status().isOk());
    }
}
