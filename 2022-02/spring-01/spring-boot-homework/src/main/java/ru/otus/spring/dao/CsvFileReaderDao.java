package ru.otus.spring.dao;

import ru.otus.spring.domain.Task;

import java.util.List;

public interface CsvFileReaderDao {

    List<Task> getTaskList();
}
