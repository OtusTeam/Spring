package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CsvReaderDaoSimple implements CsvReaderDao {

    @Value("${resource.csv.file}")
    private String csvFilePath;

    @Override
    public List<String[]> readAll() {
        try (InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(csvFilePath)));
             CSVReader reader = new CSVReader(streamReader)) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

}
