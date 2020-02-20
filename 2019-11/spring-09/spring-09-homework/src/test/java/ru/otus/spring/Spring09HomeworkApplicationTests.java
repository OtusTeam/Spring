package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Spring09HomeworkApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@DisplayName("Check bean creating")
	@Test
	void contextLoads() {
		assertThat(applicationContext.getBean(AuthorRepository.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookRepository.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(CommentRepository.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreRepository.class)).isNotEqualTo(null);
	}
}
