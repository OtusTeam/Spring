package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repositories.AuthorRepositoryJpa;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.repositories.CommentRepositoryJpa;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Spring11HomeworkApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@DisplayName("Check bean creating")
	@Test
	void contextLoads() {
		assertThat(applicationContext.getBean(AuthorRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentRepositoryJpa.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreRepository.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(AuthorService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreService.class)).isNotEqualTo(null);
	}
}
