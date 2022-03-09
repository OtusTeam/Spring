import com.opencsv.CSVReader;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.dao.CsvFileReaderDaoSimple;
import ru.otus.spring.domain.Task;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CsvFileReaderTest {

    private static final String CSV_PATH = "/tasks.csv";

    private static final String CONTEXT = "/spring-context.xml";

    @Test
    public void getTaskListTest() throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream(CSV_PATH)));
        List<String[]> list = csvReader.readAll();
        List<Task> taskList = new ArrayList<>();
        if (!list.isEmpty()) {
            list.forEach(l -> {
                String[] str = l[0].replaceAll("\"", "").trim().split("\\;");
                if (str.length == 3) {
                    String[] responses = str[1].trim().split("\\,");
                    if (responses.length > 0) {
                        List<String> responseList = List.of(responses);
                        taskList.add(new Task(str[0], responseList, str[2]));
                    }
                }
            });
        }

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
