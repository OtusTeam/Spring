package com.datasrc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request implements DataForSending<Long> {

    private final RequestId id;
    private final long seed;

    @JsonCreator
    public Request(@JsonProperty("id") RequestId id, @JsonProperty("seed") long seed) {
        this.id = id;
        this.seed = seed;
    }

    @Override
    public long id() {
        return id.id();
    }

    @Override
    public Long data() {
        return seed;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", seed=" + seed +
                '}';
    }
}
