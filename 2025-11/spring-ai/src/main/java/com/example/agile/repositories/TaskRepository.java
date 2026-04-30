package com.example.agile.repositories;


import com.example.agile.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT DISTINCT t FROM Task t WHERE t.complexity = :complexity")
    List<Task> findByComplexity(@Param("complexity") Integer complexity);

    @Query(value = """
        SELECT * FROM tasks
        ORDER BY embedding <=> cast(:embedding as vector)
        LIMIT :limit
        """, nativeQuery = true)
    List<Task> findTopKSimilar(@Param("embedding") String embeddingString,
                               @Param("limit") int limit);
}