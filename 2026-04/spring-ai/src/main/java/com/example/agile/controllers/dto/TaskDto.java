package com.example.agile.controllers.dto;

import com.example.agile.entities.Task;

public record TaskDto(String requirement,
                      String userStory,
                      String acceptanceCriteria,
                      Integer complexity) {


    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getRequirement(), task.getUserStory(), task.getAcceptanceCriteria(), task.getComplexity());
    }
}
