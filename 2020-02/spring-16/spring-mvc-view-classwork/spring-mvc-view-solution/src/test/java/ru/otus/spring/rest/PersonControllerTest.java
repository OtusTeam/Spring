package ru.otus.spring.rest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @org.junit.jupiter.api.Test
    void listPage() {
    }
}