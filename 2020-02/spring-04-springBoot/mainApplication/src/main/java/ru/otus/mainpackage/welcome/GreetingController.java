package ru.otus.mainpackage.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private final Greeting greeting;

    public GreetingController(Greeting greeting) {
        logger.info("Я НАСТОЯЩИЙ БИН!!!");
        this.greeting = greeting;
    }

    //http://localhost:8080/hello/jone
    @RequestMapping(method = RequestMethod.GET, value="/hello/{name}")
    public Map<String, String> sayHello(@PathVariable("name") String name) {
        return this.greeting.sayHello(name);
    }
}
