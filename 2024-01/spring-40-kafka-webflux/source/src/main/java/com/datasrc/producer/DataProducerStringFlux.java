package com.datasrc.producer;

import com.datasrc.model.StreamData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import java.time.Duration;
import java.util.function.BiFunction;
import reactor.core.scheduler.Scheduler;

@Service("dataProducerFlux")
public class DataProducerStringFlux implements DataProducer<Flux<StreamData>> {
    private static final Logger log = LoggerFactory.getLogger(DataProducerStringFlux.class);
    private final Scheduler timer;

    public DataProducerStringFlux(Scheduler timer) {
        this.timer = timer;
    }

    @Override
    public Flux<StreamData> produce(long seed) {
        log.info("produce using seed:{}", seed);
        var stringSeed = "someDataStr:";
        var dataSeq = Flux.generate(() -> seed,
                        (BiFunction<Long, SynchronousSink<Long>, Long>) (prev, sink) -> {
                            var newValue = prev + 1;
                            sink.next(newValue);
                            return newValue;
                        })
                .delayElements(Duration.ofSeconds(3), timer)
                .map(val -> new StreamData(stringSeed + val))
                .doOnNext(val -> log.info("val:{}", val));

        log.info("produce method finished");
        return dataSeq;
    }
}
