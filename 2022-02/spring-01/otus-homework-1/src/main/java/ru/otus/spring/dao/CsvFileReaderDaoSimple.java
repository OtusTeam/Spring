package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class CsvFileReaderDaoSimple implements CsvFileReaderDao {

    @Value("${resource.csv.file}")
    private String csvFilePath;

    private InputStreamReader fileReader;

    private CSVReader csvReader;

    @Override
    public List<Task> getTaskList() {
        List<Task> taskList;
        try {
            fileReader = getInputStreamReader(csvFilePath);
            csvReader = getCsvReader(fileReader);
            taskList = getTaskList(csvReader.readAll());
            fileReader.close();
            csvReader.close();
        } catch (IOException | CsvException e) {
            return Collections.emptyList();
        }

        return taskList;
    }

    private List<Task> getTaskList(List<String[]> list) {
        List<Task> taskList = new ArrayList<>();
        if (!list.isEmpty()) {
            list.forEach(l -> {
                String[] str = l[0].replaceAll("\"", "").trim().split("\\;");
                if (str.length == 3) {
                    String[] responses = str[1].trim().split("\\,");
                    if (responses.length > 0) {
                        List<String> responseList = List.of(responses);
                        taskList.add(new Task(str[0], responseList, str[2]));
                    }
                }
            });
        }

        return taskList;
    }

    private InputStreamReader getInputStreamReader(String csvFilePath) {
        return new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(csvFilePath)));
    }

    private CSVReader getCsvReader(InputStreamReader reader) {
        return new CSVReader(reader);
    }
}
