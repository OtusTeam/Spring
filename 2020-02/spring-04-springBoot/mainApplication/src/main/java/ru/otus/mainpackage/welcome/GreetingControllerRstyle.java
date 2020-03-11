package ru.otus.mainpackage.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GreetingControllerRstyle {
    private static final Logger logger = LoggerFactory.getLogger(GreetingControllerRstyle.class);

    private final Greeting greeting;

    public GreetingControllerRstyle(Greeting greeting) {
        logger.info("Я НАСТОЯЩИЙ БИН!!!");
        this.greeting = greeting;
    }

    //http://localhost:8080/hello/jone
    @RequestMapping(method = RequestMethod.GET, value="/hello/{name}")
    public Map<String, String> sayHello(@PathVariable("name") String name) {
        return this.greeting.sayHello(name);
    }
}
