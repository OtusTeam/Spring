package ru.otus.example.testconfigurationdemo.statefulservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(classes = {Service1.class, Service2.class})
//@TestPropertySource("classpath:test.properties")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest
class IntegrationTest1 {

    @Autowired
    private Service1 service1;

    //@MockBean
    @Autowired
    private Service2 service2;

    @Test
    void test1() {
        System.out.println(service1.getName() + ": " + service1.getState());
        System.out.println(service2.getName() + ": " + service2.getState());

        service1.setState("State7");
        service2.setState("State8");
    }

    @Test
    void test2() {
        System.out.println(service1.getName() + ": " + service1.getState());
        System.out.println(service2.getName() + ": " + service2.getState());

        service1.setState("State9");
        service2.setState("State10");
    }
}