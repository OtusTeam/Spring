package ru.otus.spring.dao;

import java.io.IOException;

public interface InputStreamReaderDao {

    void initReader();

    void closeReader() throws IOException;
}
