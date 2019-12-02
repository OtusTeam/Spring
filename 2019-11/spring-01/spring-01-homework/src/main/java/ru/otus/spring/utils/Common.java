package ru.otus.spring.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.spring.domain.Question;

public class Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);

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
        } catch (IOException e) {
            throw new RuntimeException("Error reading csv file: ", e);
        }
    }

    public static List<Question> parseQuestionsArray(List<String[]> questionsArray) {
        List<Question> questionList = new ArrayList<>();
        for (String[] line : questionsArray) {
            try {
                questionList.add(new Question(line));
            } catch (IllegalArgumentException e) {
                LOGGER.warn("Can't parse question {}", Arrays.toString(line));
            }
        }
        return questionList;
    }
}
