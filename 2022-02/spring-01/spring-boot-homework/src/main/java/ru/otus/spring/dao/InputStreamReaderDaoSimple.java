package ru.otus.spring.dao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.util.Objects;

@Getter
@Service
public class InputStreamReaderDaoSimple implements InputStreamReaderDao {

    @Value("${resource.csv.file}")
    private String csvFilePath;

    private InputStreamReader reader;

    @PostConstruct
    public void initReader() {
        this.reader = new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(csvFilePath)));
    }
}
