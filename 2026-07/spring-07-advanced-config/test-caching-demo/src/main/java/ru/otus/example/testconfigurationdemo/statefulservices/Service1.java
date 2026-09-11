package ru.otus.example.testconfigurationdemo.statefulservices;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class Service1 {
    @Getter
    private final String name = "Service1";

    @Getter
    @Setter
    private String state = "State1";
}
