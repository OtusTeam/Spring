package ru.otus.work.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.work.domain.CommentBook;

import java.util.List;

@RepositoryRestResource(path = "comment")
public interface CommentBookRepository extends JpaRepository<CommentBook, Long> {

    @RestResource(rel = "comments", path="comments")
    List<CommentBook> findByBookId(Long bookId);
}
