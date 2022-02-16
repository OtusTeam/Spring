package com.example.homework11_webflux.rest;

import com.example.homework11_webflux.controller.WordController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class WordControllerTest {
    @Autowired
    WordController controller;

    @Test
    public void testGet() {
        WebTestClient client = WebTestClient
                .bindToController(controller)
                .build();

        client.get()
                .uri("/api/words")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
