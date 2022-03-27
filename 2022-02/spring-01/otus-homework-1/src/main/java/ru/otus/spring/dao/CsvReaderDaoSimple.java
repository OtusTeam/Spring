package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvReaderDaoSimple implements CsvReaderDao {

    private final InputStreamReaderDaoSimple inputStreamReaderDao;
    private CSVReader reader;

    @Override
    public void initReader() {
        this.reader = new CSVReader(inputStreamReaderDao.getReader());
    }

    @Override
    public List<String[]> readAll() throws IOException, CsvException {
        return reader.readAll();
    }

    @Override
    public void closeReader() throws IOException {
        this.reader.close();
    }
}
