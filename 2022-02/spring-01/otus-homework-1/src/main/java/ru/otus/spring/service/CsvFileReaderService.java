package ru.otus.spring.service;

import ru.otus.spring.domain.Task;

import java.util.List;

public interface CsvFileReaderService {

    List<Task> getTaskList();
}
