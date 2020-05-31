package ru.otus.spring.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Common {
    private Common() {
    }

    public static Reader getResourceReader(String resource) {
        ClassLoader classLoader = Common.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resource);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found");
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static List<String[]> readCSV(Reader reader) {
        CSVReader csvReader = new CSVReader(reader);
        try {
            List<String[]> list = csvReader.readAll();
            reader.close();
            csvReader.close();
            return list;
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading csv file: ", e);
        }
    }
}
