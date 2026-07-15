package com.example.agile.services;

import com.example.agile.entities.Task;
import com.example.agile.repositories.TaskRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GenerationService {
    private final ChatModel chatModel;
    private final EmbeddingModel embeddingModel;
    private final TaskRepository taskRepository;
    private final EmbeddingSimilarityService similarityService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String PROMPT_TEMPLATE = """
            You are an Agile expert. Generate a User Story, acceptance criteria, and complexity estimate for the following requirement:
               "%s"
            
               The response should be in JSON format without explanation:
               {
               "userStory": "As a <role>, I want <action> so that <goal>",
               "acceptanceCriteria": "- item 1\\n- item 2\\n- item 3",
               "complexity": a number from 1 to 5
               }
            """;

    @Transactional
    public Task generateOrGetTask(String requirement) {
        float[] requirementEmbedding = embeddingModel.embed(requirement);
        Task existingTask = similarityService.findSimilarTask(requirementEmbedding);

        if (existingTask != null) {
            return existingTask;
        }

        String prompt = String.format(PROMPT_TEMPLATE, requirement);
        String aiResponse = chatModel.call(prompt);

        Task newTask = parseTask(requirement, aiResponse, requirementEmbedding);
        return taskRepository.save(newTask);
    }

    private Task parseTask(String requirement, String aiResponse, float[] requirementEmbedding) {
        try {
            JsonNode root = objectMapper.readTree(aiResponse);
            String userStory = root.get("userStory").asText();
            String acceptanceCriteria = root.get("acceptanceCriteria").asText();
            int complexity = root.get("complexity").asInt();
            return new Task(UUID.randomUUID(), requirement, userStory, acceptanceCriteria, complexity, requirementEmbedding);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка парсинга ответа модели: " + aiResponse, e);
        }
    }
}
