import com.opencsv.CSVReader;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.dao.CsvFileReaderDaoSimple;
import ru.otus.spring.domain.Task;

import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CsvFileReaderTest {

    private static final String CSV_PATH = "/home/kirill/IdeaProjects/otus/homework/otus-homework/src/main/resources/tasks.csv";

    private static final String CONTEXT = "/spring-context.xml";

    @Test
    public void getTaskListTest() throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(CSV_PATH));
        List<Task> taskList = CsvFileReaderDaoSimple.getTaskList(csvReader.readAll());
        assertEquals(taskList.size(), 4);
    }

    @Test
    public void getContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT);
        assertEquals(context.getBeanFactory().getBeanDefinitionCount(), 2);
        assertTrue(List.of(context.getBeanFactory().getBeanDefinitionNames()).contains("csvFileReaderService"));
        assertTrue(List.of(context.getBeanFactory().getBeanDefinitionNames()).contains("csvFileReaderDao"));
    }
}
