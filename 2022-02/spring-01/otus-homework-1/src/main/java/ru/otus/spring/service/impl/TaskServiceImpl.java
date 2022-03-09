package ru.otus.spring.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CsvFileReaderDao;
import ru.otus.spring.domain.Task;
import ru.otus.spring.service.TaskService;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class TaskServiceImpl implements TaskService {

    @Value("${passing.score}")
    private Integer passingScore;

    private final CsvFileReaderDao dao;

    @Override
    public List<Task> getTaskList() {
        return dao.getTaskList();
    }

    @Override
    public boolean checkPass(Integer passCount) {
        return passingScore.compareTo(passCount) <= 0;
    }
}
