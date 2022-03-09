package ru.otus.spring.service;

import ru.otus.spring.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTaskList();

    boolean checkPass(Integer passCount);
}
