package ru.otus.spring.service.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.dao.CsvFileReaderDao;
import ru.otus.spring.domain.Task;
import ru.otus.spring.service.CsvFileReaderService;

import java.util.List;

@Data
@NoArgsConstructor
public class CsvFileReaderServiceImpl implements CsvFileReaderService {

    private CsvFileReaderDao dao;

    @Override
    public List<Task> getTaskList() {
        return dao.getTaskList();
    }
}
