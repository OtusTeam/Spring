package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.configs.ApplicationProps;
import ru.otus.spring.configs.ExamProps;
import ru.otus.spring.service.ExamService;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProps.class, ExamProps.class})
public class Spring04HomeworkApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(Spring04HomeworkApplication.class, args);
        var examService = context.getBean(ExamService.class);
        examService.proceedExam();
    }

}
