package ru.otus.spring.service.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CsvFileReaderDao;
import ru.otus.spring.domain.Task;
import ru.otus.spring.service.CsvFileReaderService;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CsvFileReaderServiceImpl implements CsvFileReaderService {

    private final CsvFileReaderDao dao;

    @Override
    public List<Task> getTaskList() {
        return dao.getTaskList();
    }
}
