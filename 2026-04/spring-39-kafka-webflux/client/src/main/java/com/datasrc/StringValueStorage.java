package com.datasrc;

import com.datasrc.model.RequestId;
import com.datasrc.model.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.publisher.Sinks;
import reactor.util.annotation.NonNull;

public class StringValueStorage implements Sinks.EmitFailureHandler {

    private final Sinks.Many<ResponseData> sink;
    private final ConnectableFlux<ResponseData> sinkConnectable;
    private static final Logger log = LoggerFactory.getLogger(StringValueStorage.class);

    public StringValueStorage() {
        sink = Sinks.many().multicast().onBackpressureBuffer();
        sinkConnectable = sink.asFlux().publish();
        sinkConnectable.connect();
    }

    public void put(RequestId requestId, StringValue value) {
        log.info("put. requestId:{}, value:{}", requestId, value);
        sink.emitNext(new ResponseData(requestId, value), this);
    }

    public Mono<StringValue> get(RequestId requestId) {
        return Mono.from(sinkConnectable
                .filter(responseData -> {
                    log.info("waiting:{}, fact:{}", requestId, responseData.requestId);
                    return responseData.requestId.id() == requestId.id();
                })
                .map(responseData -> responseData.stringValue));
    }


    @Override
    public boolean onEmitFailure(@NonNull SignalType signalType, @NonNull Sinks.EmitResult emitResult) {
        return false;
    }

    private record ResponseData(RequestId requestId, StringValue stringValue) {
    }
}
