package ru.otus.spring.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.User;
import ru.otus.spring.repositories.UserRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Data Jpa для работы с пользователями")
@DataJpaTest
public class UserRepositoryTest {


    private final String TEST_USERNAME = "USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен загружать информацию о нужном пользователе по имени")
    @Test
    void shouldFindExpectedAuthorByName() {
        val actualAuthor = userRepository.findByUsername(TEST_USERNAME);
        val expectedAuthor = findByUsername(TEST_USERNAME);
        assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    private User findByUsername(String username) {
        TypedQuery<User> query = em.getEntityManager().createQuery("select u " +
                        "from User u " +
                        "where u.username = :username",
                User.class);
        query.setParameter("username", username);
        List<User> resultList = query.getResultList();
        return  !resultList.isEmpty() ? resultList.get(0) : null;
    }
}
