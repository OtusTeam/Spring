package ru.otus.example.greetingmicroservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @Autowired
    private NamesMicroServiceClient namesMicroServiceClient;

    @GetMapping("greetings/simple")
    public String simpleGreeting(){
        return "Нихао! Всем по нихао!";
    }

    @GetMapping("greetings/from-config-server")
    public String greetingFromConfigServer(@Value("${greeting}") String greeting){
        return greeting;
    }

    @GetMapping("greetings/from-external-service")
    public String greetingFromEternalSservice(){
        return namesMicroServiceClient.randomName() + ": Здравствуйте и не говорите, что не здравтсвуйте!";
    }

}
