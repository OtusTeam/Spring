package com.example.agile.services;


import com.example.agile.entities.Task;
import com.example.agile.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingSimilarityService {
    @Value("${story.similarity.threshold:0.85}")
    private double threshold;
    @Value("${story.similarity.top-k:5}")
    private int topK;

    private final TaskRepository taskRepository;

    public double cosineSimilarityFromDistance(double cosineDistance) {
        return 1.0 - cosineDistance;
    }

    public Task findSimilarTask(float[] newEmbedding) {
        String embeddingStr = floatArrayToPgVectorString(newEmbedding);

        List<Task> candidates = taskRepository.findTopKSimilar(embeddingStr, topK);

        for (Task candidate : candidates) {
            double distance = computeCosineDistance(newEmbedding, candidate.getEmbedding());
            double similarity = 1.0 - distance;
            if (similarity >= threshold) {
                return candidate;
            }
        }
        return null;
    }

    private double computeCosineDistance(float[] a, float[] b) {
        if (a.length != b.length) return 1.0;
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        if (normA == 0.0 || normB == 0.0) return 1.0;
        double cosineSimilarity = dot / (Math.sqrt(normA) * Math.sqrt(normB));
        return 1.0 - cosineSimilarity;
    }

    private String floatArrayToPgVectorString(float[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}