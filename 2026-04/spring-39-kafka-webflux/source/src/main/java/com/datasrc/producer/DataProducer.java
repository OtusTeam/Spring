package com.datasrc.producer;

public interface DataProducer<T> {

    T produce(long seed);
}
