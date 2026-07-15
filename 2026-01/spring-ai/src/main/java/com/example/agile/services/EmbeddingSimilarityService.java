package com.example.agile.services;


import com.example.agile.entities.Task;
import com.example.agile.repositories.TaskRepository;
import com.pgvector.PGvector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingSimilarityService {
    private final TaskRepository taskRepository;

    public Task findSimilarTask(float[] newEmbedding) {
        String embeddingStr = new PGvector(newEmbedding).toString();

        List<Task> candidates = taskRepository.findTopSimilar(embeddingStr);
        if(candidates.isEmpty()){
            return null;
        }
        return candidates.getFirst();
    }
}