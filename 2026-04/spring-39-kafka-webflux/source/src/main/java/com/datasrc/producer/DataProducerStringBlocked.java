package com.datasrc.producer;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("dataProducerStringBlocked")
public class DataProducerStringBlocked implements DataProducer<String> {
    private static final Logger log = LoggerFactory.getLogger(DataProducerStringBlocked.class);

    @Override
    public String produce(long seed) {
        log.info("produce using seed:{}", seed);
        sleep();
        return String.format("someDataStr:%s", seed);
    }

    private void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
