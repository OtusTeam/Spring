package ru.otus.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.service.GreetingService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest({GreetingController.class,  GreetingControllerRstyle.class})
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    void sayHelloTest() throws Exception {
        String name = "Mr.Test";
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        String expectedResult = "{\"Mr.Test\":\"Hello, Mr.Test\"}";

        when(greetingService.sayHello(name)).thenReturn(map);
        MockHttpServletResponse result = mockMvc.perform(get(String.format("/hello?name=%s", name)))
                .andReturn()
                .getResponse();

        verify(greetingService, times(1)).sayHello(name);
        System.out.println();
        assertThat(result.getContentAsString()).isEqualTo(expectedResult);
    }

    @Test
    void sayHelloTestMock() throws Exception {
        String name = "Mr.Test";
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        String expectedResult = "{\"Mr.Test\":\"Hello, Mr.Test\"}";

        when(greetingService.sayHello(name)).thenReturn(map);
        MockHttpServletResponse result = mockMvc.perform(get(String.format("/hello/%s", name)))
                .andReturn()
                .getResponse();

        verify(greetingService, times(1)).sayHello(name);
        System.out.println();
        assertThat(result.getContentAsString()).isEqualTo(expectedResult);
    }
}