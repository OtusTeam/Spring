package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Person;
import ru.otus.spring.rest.dto.PersonDto;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.SystemInfoService;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.spring.rest.GlobalExceptionHandler.ERROR_STRING;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonService personService;

    @MockitoBean
    private SystemInfoService systemInfoService;

    @Test
    void shouldReturnCorrectPersonsList() throws Exception {
        List<Person> persons = List.of(new Person(1, "Person1"), new Person(2, "Person2"));
        given(personService.findAll()).willReturn(persons);

        List<PersonDto> expectedResult = persons.stream()
                .map(PersonDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnExpectedErrorWhenPersonsNotFound() throws Exception {
        given(personService.findAll()).willReturn(List.of());

        mvc.perform(get("/api/persons"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(ERROR_STRING));
    }

    @Test
    void shouldCorrectSaveNewPerson() throws Exception {
        Person person = new Person(1, "Person1");
        given(personService.save(any())).willReturn(person);
        String expectedResult = mapper.writeValueAsString(PersonDto.toDto(person));

        mvc.perform(post("/api/persons").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }
}