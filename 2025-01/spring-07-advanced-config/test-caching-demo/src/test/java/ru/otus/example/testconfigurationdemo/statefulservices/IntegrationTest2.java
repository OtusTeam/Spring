package ru.otus.example.testconfigurationdemo.statefulservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegrationTest2 {

    @Autowired
    private Service1 service1;

    @Autowired
    private Service2 service2;

    @Test
    void test3() {
        System.out.println(service1.getName() + ": " + service1.getState());
        System.out.println(service2.getName() + ": " + service2.getState());

        service1.setState("State3");
        service2.setState("State4");
    }

    @Test
    void test4() {
        System.out.println(service1.getName() + ": " + service1.getState());
        System.out.println(service2.getName() + ": " + service2.getState());

        service1.setState("State5");
        service2.setState("State6");
    }
}