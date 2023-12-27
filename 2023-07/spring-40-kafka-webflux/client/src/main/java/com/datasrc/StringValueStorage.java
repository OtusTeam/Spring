package com.datasrc;

import com.datasrc.model.RequestId;
import com.datasrc.model.StringValue;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class StringValueStorage implements AutoCloseable{

    private final ScheduledExecutorService executor;
    private static final Logger log = LoggerFactory.getLogger(StringValueStorage.class);
    private final Map<RequestId, StringValue> storage = new ConcurrentHashMap<>();

    public StringValueStorage(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    public void put(RequestId requestId, StringValue value) {
        log.info("put. requestId:{}, value:{}", requestId, value);
        storage.put(requestId, value);
    }

    public Mono<StringValue> get(RequestId requestId) {
        return Mono.fromCallable(() -> {
            StringValue value;
            do
                value = executor.schedule(() -> storage.get(requestId), 100, TimeUnit.MILLISECONDS).get();
            while (value == null);
            return value;
        });
    }

    @Override
    public void close() {
        executor.shutdownNow();
    }
}
