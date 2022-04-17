package com.example.hw14_batch.repository;


import com.example.hw14_batch.model.sql.SqlGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<SqlGenre, Integer> {


}
