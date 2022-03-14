package ru.otus.spring.dao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Getter
@Service
public class InputStreamReaderBean {

    @Value("${resource.csv.file}")
    private String csvFilePath;

    private InputStreamReader reader;

    @PostConstruct
    public void init() {
        this.reader = new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(csvFilePath)));
    }

    public void closeReader() throws IOException {
        this.reader.close();
    }
}
