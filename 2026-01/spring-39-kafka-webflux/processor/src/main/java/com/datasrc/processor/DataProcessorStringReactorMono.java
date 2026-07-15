package com.datasrc.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("dataProcessorMono")
public class DataProcessorStringReactorMono implements DataProcessor<String> {
    private static final Logger log = LoggerFactory.getLogger(DataProcessorStringReactorMono.class);

    @Override
    public String process(String value) {
        log.info("processor");
        return value.toUpperCase();
    }
}
