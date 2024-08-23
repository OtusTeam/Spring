package ru.otus.example.testconfigurationdemo.statefulservices;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class Service2 {
    @Getter
    private final String name = "Service2";

    @Getter
    @Setter
    private String state = "State2";
}
