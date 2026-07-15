package com.example.agile.tools;

import com.example.agile.controllers.dto.TaskDto;
import com.example.agile.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskTool {
    private final TaskService taskService;

    @Tool(name = "getAllTask", description = "Get all tasks (User Stories) from the database")
    public List<TaskDto> getAllTask() {
        return taskService.findAll();
    }

    @Tool(name = "getTasksByComplexity", description = "Get all tasks by difficulty")
    public List<TaskDto> getTasksByComplexity(
            @ToolParam(description = "Task difficulty level (1 to 5)") Integer complexity) {
        return taskService.findByComplexity(complexity);
    }
}