package kz.yyediluly;


import kz.yyediluly.domain.Question;
import kz.yyediluly.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        service.runService();

    }
}
