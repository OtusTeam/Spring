package com.datasrc.processor;

public interface DataProcessor<T> {

    T process(T data);
}
