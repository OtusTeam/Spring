package com.example.homework7_datajpa.repository;

import com.example.homework7_datajpa.model.Book;
import com.example.homework7_datajpa.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
@Query("select c from Comment c where c.book = ?1")
    List<Comment> findByBook_Id(Optional<Book> book);
}
