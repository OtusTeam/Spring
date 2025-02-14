package ru.otus.spring.rest;

import java.time.Duration;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;
import ru.otus.spring.BaseContainerTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnnotatedControllerTest extends BaseContainerTest {

    @Autowired
    private WebTestClient webTestClient;
    @LocalServerPort
    private int port;


    @Test
    void oneTest() {
        //given
        var client = WebClient.create(String.format("http://localhost:%d", port));

        //when
        var result = client
                .get().uri("/flux/one")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(3))
                .block();

        //then
        assertThat(result).isEqualTo("one");
    }

    @Test
    void streamTest() {
        //given
        var client = WebClient.create(String.format("http://localhost:%d", port));
        var expectedSize = 5;

        //when
        List<String> result = client
                .get().uri("/stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .take(expectedSize)
                .timeout(Duration.ofSeconds(3))
                .collectList()
                .block();

        //then
        assertThat(result).hasSize(expectedSize)
                .contains(String.format("valStr:%s", 0),
                        String.format("valStr:%s", 1),
                        String.format("valStr:%s", 2),
                        String.format("valStr:%s", 3),
                        String.format("valStr:%s", 4));
    }

    @Test
    void dataTest() {
        //given
        var webTestClientForTest = webTestClient.mutate()
                .responseTimeout(Duration.ofSeconds(20))
                .build();

        //when
        var result = webTestClientForTest
                .get().uri("/flux/ten")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        //then
        var step = StepVerifier.create(result);
        StepVerifier.Step<Integer> stepResult = null;
        for (var idx = 1; idx <= 10; idx++) {
            stepResult = step.expectNext(idx);
        }
        stepResult.verifyComplete();
    }

}