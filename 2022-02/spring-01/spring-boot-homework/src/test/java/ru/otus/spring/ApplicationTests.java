package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.otus.spring.dao.CsvFileReaderDao;
import ru.otus.spring.dao.CsvReaderDao;
import ru.otus.spring.domain.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private CsvReaderDao csvReaderDao;
	@Autowired
	private CsvFileReaderDao csvFileReaderDao;

	@Value("${application.passing.score}")
	private Integer passingScore;

	@Test
	public void getTaskListTest() {
		List<Task> taskList = csvFileReaderDao.getTaskList();
		assertEquals(taskList.size(), 4);
	}

	@Test
	public void readAllTest() throws Exception {
		List<String[]> list = csvReaderDao.readAll();
		assertEquals(list.size(), 4);

	}

	@Test
	public void checkPassTest() {
		assertFalse(passingScore.compareTo(2) <= 0);
		assertTrue(passingScore.compareTo(3) <= 0);
		assertTrue(passingScore.compareTo(4) <= 0);
	}

	@Test
	public void localeTest() {
		String message2 = messageSource.getMessage("messages.name.text", null, LocaleContextHolder.getLocale());
		assertEquals(message2, "Ваше имя:");

	}

}
