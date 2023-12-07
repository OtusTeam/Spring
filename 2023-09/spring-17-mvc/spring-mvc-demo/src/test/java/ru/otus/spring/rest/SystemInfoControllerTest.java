package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.SystemInfo;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.service.SystemInfoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(SystemInfoController.class)
@Import(SystemInfoService.class)
class SystemInfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonRepository repository;

    @Autowired
    private SystemInfoService systemInfoService;

    @Test
    void shouldReturnCorrectServerSystemInfo() throws Exception {
        SystemInfo expectedSystemInfo = systemInfoService.getSystemInfo();
        mvc.perform(get("/server/system/info"))
                .andExpect(content().json(mapper.writeValueAsString(expectedSystemInfo)));
    }
}