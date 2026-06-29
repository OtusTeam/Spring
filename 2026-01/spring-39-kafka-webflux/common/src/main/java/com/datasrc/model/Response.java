package com.datasrc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response implements DataForSending<StringValue> {
    private final ResponseId id;
    private final StringValue stringValue;

    @JsonCreator
    public Response(@JsonProperty("id") ResponseId id, @JsonProperty("stringValue") StringValue stringValue) {
        this.id = id;
        this.stringValue = stringValue;
    }

    @Override
    public long id() {
        return id.id();
    }

    @Override
    public StringValue data() {
        return stringValue;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", stringValue=" + stringValue +
                '}';
    }
}
