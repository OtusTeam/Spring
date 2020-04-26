package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repository.AuthorRepositoryJpa;
import ru.otus.spring.repository.BookRepositoryJpa;
import ru.otus.spring.repository.CommentRepositoryJpa;
import ru.otus.spring.repository.GenreRepositoryJpa;
import ru.otus.spring.rest.AuthorController;
import ru.otus.spring.rest.BookController;
import ru.otus.spring.rest.CommentController;
import ru.otus.spring.rest.GenreController;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Spring16HomeworkApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@DisplayName("Check bean creating")
	@Test
	void contextLoads() {
		assertThat(applicationContext.getBean(AuthorRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(AuthorService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(AuthorController.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookController.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentController.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreController.class)).isNotEqualTo(null);
	}
}
