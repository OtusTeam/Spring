package com.example.agile.services;

import com.example.agile.controllers.dto.TaskDto;
import com.example.agile.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    @Transactional(readOnly = true)
    public List<TaskDto> findByComplexity(Integer complexity) {
        return repository.findByComplexity(complexity).stream().map(TaskDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<TaskDto> findAll() {
        return repository.findAll().stream().map(TaskDto::toDto).toList();
    }
}
