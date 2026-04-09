package ru.demo.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestEncoder implements Encoder {
    private static final Logger log = LoggerFactory.getLogger(RequestEncoder.class);
    private final Encoder defaultEncoder;

    public RequestEncoder(Encoder defaultEncoder) {
        this.defaultEncoder = defaultEncoder;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        log.info("encode value:{}", object);
        defaultEncoder.encode(object, bodyType, template);
    }
}
