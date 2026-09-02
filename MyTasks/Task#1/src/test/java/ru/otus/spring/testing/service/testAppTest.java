package ru.otus.spring.testing.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.testing.dao.TestQuestionDAOCSV;
import ru.otus.spring.testing.dao.TestQuestionDAOMock;
import ru.otus.spring.testing.domain.SimpleQuestionImp;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class testAppTest {
    @Mock
    private TestQuestionDAOMock dao;
    @InjectMocks
    private TestAppSimple personService;

    @Test
    void getNextQuestion(){
        given(personService.getNextQuestion()).willReturn(new SimpleQuestionImp("How many push-ups can Chuck Norris do", "all"));
    }


}
