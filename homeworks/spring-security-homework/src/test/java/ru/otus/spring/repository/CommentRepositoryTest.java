package ru.otus.spring.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Data Jpa для работы с комментариями")
@DataJpaTest
class CommentRepositoryTest {

    private static final String EXPECTED_NEW_COMMENT_TEXT = "Новый комментарий";
    private static final long THIRD_COMMENT_ID = 3L;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен сохранять новый комментарий")
    @Test
    void shouldSaveNewComment() {
        val actualNewComment = commentRepository.save(new Comment(EXPECTED_NEW_COMMENT_TEXT));
        val expectedNewComment = em.find(Comment.class, THIRD_COMMENT_ID);
        assertThat(actualNewComment)
                .usingRecursiveComparison().isEqualTo(expectedNewComment);
    }
}
