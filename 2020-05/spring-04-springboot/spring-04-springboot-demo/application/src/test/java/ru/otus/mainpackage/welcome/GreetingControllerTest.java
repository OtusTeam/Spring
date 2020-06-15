package ru.otus.mainpackage.welcome;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest({GreetingController.class,  GreetingControllerRstyle.class})
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Greeting greeting;

    @Test
    public void sayHelloTest() throws Exception {
        String name = "Mr.Test";
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        String expectedResult = "{\"Mr.Test\":\"Hello, Mr.Test\"}";

        when(greeting.sayHello(name)).thenReturn(map);
        MockHttpServletResponse result = mockMvc.perform(get(String.format("/hello?name=%s", name)))
                .andReturn()
                .getResponse();

        verify(greeting, times(1)).sayHello(name);
        System.out.println();
        assertThat(result.getContentAsString()).isEqualTo(expectedResult);
    }

    @Test
    public void sayHelloTestMock() throws Exception {
        String name = "Mr.Test";
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        String expectedResult = "{\"Mr.Test\":\"Hello, Mr.Test\"}";

        when(greeting.sayHello(name)).thenReturn(map);
        MockHttpServletResponse result = mockMvc.perform(get(String.format("/hello/%s", name)))
                .andReturn()
                .getResponse();

        verify(greeting, times(1)).sayHello(name);
        System.out.println();
        assertThat(result.getContentAsString()).isEqualTo(expectedResult);
    }
}