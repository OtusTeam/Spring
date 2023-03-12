package ru.otus.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class GreetingServiceServiceImpl implements GreetingService {

    @Override
    public Map<String, String> sayHello(String name) {
        return Collections.singletonMap(name, "Hello, " + name);
    }
}
