package ru.otus.spring.repositories;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CommentRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void testCommentSave() {
        Comment comment = new Comment("comment");
        commentRepository.save(comment);
        assertThat(comment.getId()).isNotNull().isNotEmpty();
    }

    @Test
    void findAll() {
        commentRepository.save(new Comment("comment1"));
        commentRepository.save(new Comment("comment2"));
        commentRepository.save(new Comment("comment3"));
        List<Comment> comments = mongoTemplate.findAll(Comment.class);
        assertThat(comments.size()).isEqualTo(3);
    }
}
