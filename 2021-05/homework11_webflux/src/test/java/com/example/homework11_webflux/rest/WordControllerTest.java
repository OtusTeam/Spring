package com.example.homework11_webflux.rest;

import com.example.homework11_webflux.controller.WordController;
import com.example.homework11_webflux.model.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class WordControllerTest {
    @Autowired
    WordController controller;


    @Test
    public void testGetAll() {

        WebTestClient client = WebTestClient
                .bindToController(controller)
                .build();

        client.get()
                .uri("/words")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Word.class).hasSize(0);
    }


    @Test
    public void testDelete() {
        WebTestClient client = WebTestClient
                .bindToController(controller)
                .build();

        client.delete()
                .uri("/words/0")
                .exchange()
                .expectStatus()
                .isNoContent();
    }


}
