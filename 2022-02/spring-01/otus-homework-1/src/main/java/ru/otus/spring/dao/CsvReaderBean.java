package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvReaderBean {

    private final InputStreamReaderBean fileReader;
    private CSVReader reader;

    @PostConstruct
    public void init() {
        this.reader = new CSVReader(fileReader.getReader());
    }

    public List<String[]> readAll() throws IOException, CsvException {
        return reader.readAll();
    }

    public void closeReader() throws IOException {
        this.reader.close();
    }
}
