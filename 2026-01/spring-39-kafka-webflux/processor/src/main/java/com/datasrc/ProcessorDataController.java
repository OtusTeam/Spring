package com.datasrc;


import com.datasrc.model.StreamData;
import com.datasrc.processor.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
public class ProcessorDataController {
    private static final Logger log = LoggerFactory.getLogger(ProcessorDataController.class);

    private final DataProcessor<Flux<StreamData>> dataProcessorStringReactorFlux;
    private final WebClient client;

    public ProcessorDataController(WebClient client,
                                   @Qualifier("dataProcessorFlux") DataProcessor<Flux<StreamData>> dataProcessorFlux) {
        this.dataProcessorStringReactorFlux = dataProcessorFlux;
        this.client = client;
    }

    @GetMapping(value = "/data/{seed}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StreamData> data(@PathVariable("seed") long seed) {
        log.info("request for data, seed:{}", seed);

        var srcRequest = client.get().uri(String.format("/data/%d", seed))
                .accept(MediaType.APPLICATION_NDJSON)
                .retrieve()
                .bodyToFlux(StreamData.class);

        return dataProcessorStringReactorFlux.process(srcRequest);
    }
}
