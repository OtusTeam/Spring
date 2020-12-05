package ru.otus.mainpackage.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GreetingControllerRstyle {
    private static final Logger logger = LoggerFactory.getLogger(GreetingControllerRstyle.class);

    private final GreetingService greetingService;

    public GreetingControllerRstyle(GreetingService greetingService) {
        logger.info("Я НАСТОЯЩИЙ БИН!!!");
        this.greetingService = greetingService;
    }

    //http://localhost:8080/hello/jone
    @GetMapping(value="/hello/{name}")
    public Map<String, String> sayHello(@PathVariable("name") String name) {
        return this.greetingService.sayHello(name);
    }
}
