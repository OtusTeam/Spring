package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Task;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class CsvFileReaderDaoSimple implements CsvFileReaderDao {

    private final InputStreamReaderDao inputStreamReaderDao;
    private final CsvReaderDao csvReaderDao;

    @Override
    public List<Task> getTaskList() {
        try {
            initReaders();
            List<Task> taskList = getTaskList(csvReaderDao.readAll());
            closeReaders();
            return taskList;
        } catch (IOException | CsvException e) {
            return Collections.emptyList();
        }
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

    private void initReaders() {
        inputStreamReaderDao.initReader();
        csvReaderDao.initReader();
    }

    private void closeReaders() throws IOException {
        inputStreamReaderDao.closeReader();
        csvReaderDao.closeReader();
    }
}
