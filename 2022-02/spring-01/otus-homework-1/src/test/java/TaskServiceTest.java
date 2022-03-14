import com.opencsv.CSVReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.Main;
import ru.otus.spring.domain.Task;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskServiceTest {

    private static final String CSV_PATH = "/tasks.csv";

    private static final Integer PASS_SCORE = 3;

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
    public void checkPassTest() {
        assertFalse(PASS_SCORE.compareTo(2) <= 0);
        assertTrue(PASS_SCORE.compareTo(3) <= 0);
        assertTrue(PASS_SCORE.compareTo(4) <= 0);
    }

    @Test
    public void getContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        assertEquals(context.getBeanFactory().getBeanDefinitionCount(), 10);
        assertTrue(List.of(context.getBeanFactory().getBeanDefinitionNames()).contains("taskServiceImpl"));
        assertTrue(List.of(context.getBeanFactory().getBeanDefinitionNames()).contains("csvFileReaderDaoSimple"));
    }
}
