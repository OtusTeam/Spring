package ru.otus.mainpackage.welcome;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingService implements Greeting {

    @Override
    public Map<String, String> sayHello(String name) {
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        return map;
    }
}
