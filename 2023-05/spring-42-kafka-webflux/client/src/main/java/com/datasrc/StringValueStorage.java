package com.datasrc;

import com.datasrc.model.RequestId;
import com.datasrc.model.StringValue;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class StringValueStorage {
    private static final Logger log = LoggerFactory.getLogger(StringValueStorage.class);
    private final Map<RequestId, StringValue> storage = new ConcurrentHashMap<>();

    public void put(RequestId requestId, StringValue value) {
        log.info("put. requestId:{}, value:{}", requestId, value);
        storage.put(requestId, value);
    }

    public Mono<StringValue> get(RequestId requestId) {
        return Mono.fromCallable(() -> {
            StringValue value;
            do
                value = storage.get(requestId);
            while (value == null);
            return value;
        });
    }

}
