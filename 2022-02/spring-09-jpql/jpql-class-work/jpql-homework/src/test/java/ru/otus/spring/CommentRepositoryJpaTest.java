package ru.otus.spring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Comment;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.CommentRepositoryJpa;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    private static final String EXPECTED_NEW_COMMENT_TEXT = "Новый комментарий";
    private static final long FIRST_COMMENT_ID = 1L;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен сохранять новый комментарий")
    @Test
    void shouldSaveNewComment() {
        val actualNewComment = commentRepository.save(new Comment(EXPECTED_NEW_COMMENT_TEXT));
        val expectedNewComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(actualNewComment).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedNewComment);
    }
}
