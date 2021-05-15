package ru.otus.spring.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private RouterFunction<ServerResponse> route;

    @Test
    public void testRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/func/person")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
