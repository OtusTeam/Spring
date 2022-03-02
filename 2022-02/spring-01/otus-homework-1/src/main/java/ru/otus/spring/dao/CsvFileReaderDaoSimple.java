package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.Task;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class CsvFileReaderDaoSimple implements CsvFileReaderDao {

    private FileReader fileReader;

    @Override
    public List<Task> getTaskList() {
        List<Task> taskList;
        try {
            CSVReader csvReader = new CSVReader(fileReader);
            taskList = getTaskList(csvReader.readAll());
            fileReader.close();
            csvReader.close();
        } catch (IOException | CsvException e) {
            return Collections.emptyList();
        }

        return taskList;
    }

    public static List<Task> getTaskList(List<String[]> list) {
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
}
