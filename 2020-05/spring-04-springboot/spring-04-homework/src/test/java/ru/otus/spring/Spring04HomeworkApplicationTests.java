package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.configs.ApplicationProps;
import ru.otus.spring.configs.ExamProps;
import ru.otus.spring.configs.Localization;
import ru.otus.spring.dao.TopicRepository;
import ru.otus.spring.dao.TopicRepositoryImpl;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.TopicPresentationService;
import ru.otus.spring.service.TopicService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Spring04HomeworkApplicationTests {

    @Autowired
    ApplicationContext ctx;

    @Test
    void contextLoads() {
        assertThat(ctx.getBean(ApplicationProps.class)).isNotNull();
        assertThat(ctx.getBean(ExamProps.class)).isNotNull();
        assertThat(ctx.getBean(TopicRepository.class)).isNotNull();
        assertThat(ctx.getBean(ExamService.class)).isNotNull();
        assertThat(ctx.getBean(TopicPresentationService.class)).isNotNull();
        assertThat(ctx.getBean(TopicService.class)).isNotNull();
    }

}
