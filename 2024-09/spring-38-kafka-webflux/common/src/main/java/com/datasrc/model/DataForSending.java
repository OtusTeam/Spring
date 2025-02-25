package com.datasrc.model;

public interface DataForSending<T> {
    long id();

    T data();
}
