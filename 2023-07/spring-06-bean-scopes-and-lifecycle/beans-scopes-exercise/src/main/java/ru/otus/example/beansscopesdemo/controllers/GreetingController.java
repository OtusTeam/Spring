package ru.otus.example.beansscopesdemo.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.example.beansscopesdemo.services.GreetingService;

@Controller
public class GreetingController {
    private final GreetingService singletonGreetingService;
    private final GreetingService prototypeGreetingService1;
    private final GreetingService prototypeGreetingService2;
    private final GreetingService sessionGreetingService;
    private final GreetingService requestGreetingService;

    public GreetingController(GreetingService singletonGreetingService,
                              @Qualifier("prototypeGreetingService")
                                      GreetingService prototypeGreetingService1,
                              @Qualifier("prototypeGreetingService")
                                      GreetingService prototypeGreetingService2,
                              GreetingService sessionGreetingService,
                              GreetingService requestGreetingService
    ) {
        this.singletonGreetingService = singletonGreetingService;
        this.prototypeGreetingService1 = prototypeGreetingService1;
        this.prototypeGreetingService2 = prototypeGreetingService2;
        this.sessionGreetingService = sessionGreetingService;
        this.requestGreetingService = requestGreetingService;
    }

    @GetMapping("/")
    public String greetingPage(Model model) {
        model.addAttribute("singletonGreeting", singletonGreetingService.greeting());
        model.addAttribute("sessionGreeting", sessionGreetingService.greeting());
        model.addAttribute("requestGreeting", requestGreetingService.greeting());
        model.addAttribute("prototype1Greeting", prototypeGreetingService1.greeting());
        model.addAttribute("prototype2Greeting", prototypeGreetingService2.greeting());
        return "index";
    }
}
