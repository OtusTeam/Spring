package com.example.agile.controllers;

import com.example.agile.controllers.dto.ChatRequest;
import com.example.agile.controllers.dto.TaskDto;
import com.example.agile.services.ChatService;
import com.example.agile.services.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private GenerationService generationService;

    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(chatService.sendMessage(request.message()));
    }

    @PostMapping
    public ResponseEntity<TaskDto> generate(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(TaskDto.toDto(generationService.generateOrGetTask(request.message())));
    }
}