package ru.otus.mainpackage.welcome;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate; //call remote REST services

    @MockBean
    private GreetingController greetingController;

    @Test
    public void sayHelloTest() {
        final String name = "Mr.Test";
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/hello?name=" + name, Map.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody(), "body not null");
        assertTrue(entity.getBody().containsKey(name));
    }

    @Test
    public void sayHelloTestMock() {
        final String name = "Mr.Test";

        when(greetingController.sayHello(anyString())).thenReturn(Collections.singletonMap(name, name));

        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/hello/" + name, Map.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody(), "body not null");
        assertTrue(entity.getBody().containsKey(name));
    }

}