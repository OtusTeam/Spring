package ru.orus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.orus.spring.dao.AuthorDao;
import ru.orus.spring.dao.BooksDao;
import ru.orus.spring.dao.GenreDao;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Spring07HomeworkApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @DisplayName("Check bean creating")
    @Test
    void contextLoads() {
        assertThat(applicationContext.getBean(AuthorDao.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(BooksDao.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(GenreDao.class)).isNotEqualTo(null);
    }

}
