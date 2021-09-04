package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.spring.service.MyService;

import java.util.Locale;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDemoApplication.class, args);
        MyService service = ctx.getBean(MyService.class);

        System.out.println(service.sayHello());

        MessageSource msg = ctx.getBean(MessageSource.class);

        System.out.println(msg.getMessage(
                "strings.goodbye",
                new String[] {"Ivan"},
                Locale.forLanguageTag("ru-RU")
        ));
    }

}
