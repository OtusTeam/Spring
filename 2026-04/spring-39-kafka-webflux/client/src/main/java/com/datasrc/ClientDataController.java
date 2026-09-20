package com.datasrc;


import com.datasrc.config.ReactiveSender;
import com.datasrc.model.Request;
import com.datasrc.model.RequestId;
import com.datasrc.model.StreamData;
import com.datasrc.model.StringValue;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//  http://localhost:8082/data/5
@RestController
public class ClientDataController {
    private static final Logger log = LoggerFactory.getLogger(ClientDataController.class);
    private final AtomicLong idGenerator = new AtomicLong(0);

    private final WebClient webClient;
    private final ReactiveSender<Long, Request> requestSender;

    private final StringValueStorage stringValueStorage;

    public ClientDataController(WebClient webClient, ReactiveSender<Long, Request> requestSender, StringValueStorage stringValueStorage) {
        this.webClient = webClient;
        this.requestSender = requestSender;
        this.stringValueStorage = stringValueStorage;
    }

    @GetMapping(value = "/data/{seed}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StreamData> data(@PathVariable("seed") long seed) {
        log.info("request for data, seed:{}", seed);

        return webClient.get().uri(String.format("/data/%d", seed))
                .accept(MediaType.APPLICATION_NDJSON)
                .retrieve()
                .bodyToFlux(StreamData.class)
                .doOnNext(val -> log.info("val:{}", val));
    }

    @GetMapping(value = "/data-mono/{seed}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<StringValue> dataMono(@PathVariable("seed") long seed) {
        log.info("request for string data-mono, seed:{}", seed);
        var request = new Request(new RequestId(idGenerator.incrementAndGet()), seed);

        return requestSender.send(request, requestSend -> log.info("send ok: {}", requestSend))
                .flatMap(v -> stringValueStorage.get(new RequestId(v.correlationMetadata().id())))
                .doOnNext(stringValue -> log.info("value for client:{}", stringValue));
    }
}
