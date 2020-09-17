package ru.otus.mainpackage.welcome;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingServiceServiceImpl implements GreetingService {

    @Override
    public Map<String, String> sayHello(String name) {
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        return map;
    }
}
