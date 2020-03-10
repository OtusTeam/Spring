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

    public GreetingController(@Qualifier("SingletonGreetingService") GreetingService singletonGreetingService,
                              @Qualifier("PrototypeGreetingService")GreetingService prototypeGreetingService1,
                              @Qualifier("PrototypeGreetingService")GreetingService prototypeGreetingService2,
                              @Qualifier("SessionGreetingService")GreetingService sessionGreetingService,
                              @Qualifier("RequestGreetingService")GreetingService requestGreetingService
    ) {
        this.singletonGreetingService = singletonGreetingService;
        this.prototypeGreetingService1 = prototypeGreetingService1;
        this.prototypeGreetingService2 = prototypeGreetingService2;
        this.sessionGreetingService = sessionGreetingService;
        this.requestGreetingService = requestGreetingService;
    }

    @GetMapping("/")
    public String greetingPage(Model model) {
        boolean isFirstGreetingSuccess = prototypeGreetingService1.isFirstGreetingSuccess();
        model.addAttribute("singletonGreeting", singletonGreetingService.greeting());
        model.addAttribute("sessionGreeting", sessionGreetingService.greeting());
        model.addAttribute("requestGreeting", requestGreetingService.greeting());
        model.addAttribute("prototype1Greeting", prototypeGreetingService1.greeting());
        model.addAttribute("prototype2Greeting", isFirstGreetingSuccess? prototypeGreetingService2.greeting(): "Пока жду");
        return "index";
    }
}
