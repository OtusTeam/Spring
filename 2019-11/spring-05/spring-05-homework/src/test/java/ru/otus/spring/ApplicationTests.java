package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repository.QuestionRepository;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionPresentationService;
import ru.otus.spring.service.QuestionService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@DisplayName("Check bean creating")
	void contextLoads() {
		assertThat(applicationContext.getBean(ExamService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(QuestionPresentationService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(QuestionService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(QuestionRepository.class)).isNotEqualTo(null);
	}
}
