package com.datasrc.processor;

import com.datasrc.model.StreamData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;

@Service("dataProcessorFlux")
public class DataProcessorStringReactorFlux implements DataProcessor<Flux<StreamData>> {
    private static final Logger log = LoggerFactory.getLogger(DataProcessorStringReactorFlux.class);
    private final Scheduler timer;

    public DataProcessorStringReactorFlux(Scheduler timer) {
        this.timer = timer;
    }

    @Override
    public Flux<StreamData> process(Flux<StreamData> dataflow) {
        log.info("processor");
        var dataSeq = dataflow
                .doOnNext(val -> log.info("in val:{}", val))
                .delayElements(Duration.ofSeconds(5), timer)
                .map(data -> new StreamData(data.value().toUpperCase()))
                .doOnNext(val -> log.info("out val:{}", val));

        log.info("processor method finished");
        return dataSeq;
    }
}
