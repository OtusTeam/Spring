package ru.otus.spring.repositories;

import java.util.List;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@DataJpaTest
@Import({CommentRepository.class})
class CommentRepositoryTest {
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 4;
    private static final long FIRST_COMMENT_ID = 1;
    private static final long FIRST_BOOK_ID = 1;
    private static final String FIRST_COMMENT = "Вообще непонимаю";
    private static final String UPDATED_COMMENT_NAME = "Nota bene";

    @Autowired
    CommentRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен корректно сохранять всю информацию о комментарии")
    @Test
    void save() {
        val book = em.find(Book.class, FIRST_BOOK_ID);
        val comment = new Comment(0, book, UPDATED_COMMENT_NAME);

        repositoryJpa.save(comment);
        assertThat(comment.getId()).isGreaterThan(0);

        val actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isNotNull().matches(s -> !s.getComment().equals(""));
    }

    @DisplayName(" должен загружать информацию о нужном комментарии по его id")
    @Test
    void findById() {
        val actualComment = repositoryJpa.findById(FIRST_COMMENT_ID);
        val expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(actualComment.isPresent()).isTrue();
        assertThat(actualComment.get().getComment()).isEqualTo(FIRST_COMMENT);
        assertThat(actualComment).isPresent().get()
                .isEqualToComparingFieldByField(expectedComment);
    }

    @DisplayName(" должен загружать список всех комментариев с полной информацией о них")
    @Test
    void findAll() {
        val comments = repositoryJpa.findAll();
        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS);
    }

    @DisplayName(" должен загружать информацию о нужном комментарии по его имени")
    @Test
    void findByName() {
        val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        List<Comment> comments = repositoryJpa.findByName(FIRST_COMMENT);
        assertThat(comments).hasSize(1);
        assertThat(comments).containsOnlyOnce(firstComment);
    }

    @DisplayName(" должен изменять текст заданного комментария по его id")
    @Test
    void updateNameById() {
        val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        String comment = firstComment.getComment();
        em.detach(firstComment);

        repositoryJpa.updateNameById(FIRST_COMMENT_ID, UPDATED_COMMENT_NAME);
        val updatedComment = em.find(Comment.class, FIRST_COMMENT_ID);

        assertThat(updatedComment.getComment()).isNotEqualTo(comment).isEqualTo(UPDATED_COMMENT_NAME);
    }

    @DisplayName(" должен удалять заданный коммент по его id")
    @Test
    void deleteById() {
        val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(firstComment).isNotNull();
        em.detach(firstComment);

        repositoryJpa.deleteById(FIRST_COMMENT_ID);
        val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);

        assertThat(deletedComment).isNull();
    }
}