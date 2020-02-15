package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repositories.AuthorRepositoryJpaImpl;
import ru.otus.spring.repositories.BookRepositoryJpaImpl;
import ru.otus.spring.repositories.CommentRepositoryJpaImpl;
import ru.otus.spring.repositories.GenreRepositoryJpaImpl;
import ru.otus.spring.service.PresentationService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Spring08HomeworkApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@DisplayName("Check bean creating")
	@Test
	void contextLoads() {
		assertThat(applicationContext.getBean(AuthorRepositoryJpaImpl.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookRepositoryJpaImpl.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentRepositoryJpaImpl.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreRepositoryJpaImpl.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(PresentationService.class)).isNotEqualTo(null);
	}
}
