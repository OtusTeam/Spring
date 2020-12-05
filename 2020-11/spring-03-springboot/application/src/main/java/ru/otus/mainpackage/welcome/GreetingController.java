package ru.otus.mainpackage.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mainpackage.configs.AppProps;


import java.util.Map;

@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService, AppProps props) {
        this.greetingService = greetingService;
        logger.info("ATTENTION! props.getMessage(): {}", props.getMessage());
    }

    //http://localhost:8080/hello?name=ddd
    @GetMapping("/hello")
    public Map<String, String> sayHello(@RequestParam(name="name") String name) {
        return this.greetingService.sayHello(name);
    }
}
