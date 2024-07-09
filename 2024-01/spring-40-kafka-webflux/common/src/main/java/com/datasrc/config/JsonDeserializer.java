package com.datasrc.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class JsonDeserializer<T> implements Deserializer<T> {
    public static final String OBJECT_MAPPER = "objectMapper";
    public static final String TYPE_REFERENCE = "typeReference";
    private final String encoding = StandardCharsets.UTF_8.name();
    private ObjectMapper mapper;
    private JavaType javaType;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        mapper = (ObjectMapper) configs.get(OBJECT_MAPPER);
        if (mapper == null) {
            throw new IllegalArgumentException("config property OBJECT_MAPPER was not set");
        }
        javaType = (JavaType) configs.get(TYPE_REFERENCE);
        if (javaType == null) {
            throw new IllegalArgumentException("config property TYPE_REFERENCE was not set");
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            } else {
                var valueAsString = new String(data, encoding);
                return mapper.readValue(valueAsString, javaType);
            }
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to StringValue", e);
        }
    }
}
