package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.rest.dto.PersonDto;
import ru.otus.spring.service.SystemInfoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    public static final String ERROR_STRING = "Таких тут нет!";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonRepository repository;

    @MockBean
    private SystemInfoService systemInfoService;

    @Test
    void shouldReturnCorrectPersonsList() throws Exception {
        List<Person> persons = List.of(new Person(1, "Person1"), new Person(2, "Person2"));
        given(repository.findAll()).willReturn(persons);

        List<PersonDto> expectedResult = persons.stream()
                .map(PersonDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectPersonByNameInRequest() throws Exception {
        Person person = new Person(1, "Person1");
        given(repository.findByName(person.getName())).willReturn(List.of(person));
        PersonDto expectedResult = PersonDto.toDto(person);

        mvc.perform(get("/persons").param("name", person.getName()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectPersonByIdInPath() throws Exception {
        Person person = new Person(1, "Person1");
        given(repository.findById(1L)).willReturn(Optional.of(person));
        PersonDto expectedResult = PersonDto.toDto(person);

        mvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnExpectedErrorWhenPersonNotFound() throws Exception {
        given(repository.findById(1L)).willReturn(Optional.empty());

        mvc.perform(get("/persons").param("name", "Person1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_STRING));

        mvc.perform(get("/persons/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_STRING));
    }

    @Test
    void shouldCorrectSaveNewPerson() throws Exception {
        Person person = new Person(1, "Person1");
        given(repository.save(any())).willReturn(person);
        String expectedResult = mapper.writeValueAsString(PersonDto.toDto(person));

        mvc.perform(post("/persons").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void shouldCorrectUpdatePersonName() throws Exception {
        Person person = new Person(1, "Person1");
        given(repository.findById(1L)).willReturn(Optional.of(person));
        given(repository.save(any())).willAnswer(invocation -> invocation.getArgument(0));

        Person expectedPerson = new Person(1, "Person2");
        String expectedResult = mapper.writeValueAsString(PersonDto.toDto(expectedPerson));

        mvc.perform(patch("/persons/{id}/name", 1).param("name", expectedPerson.getName())
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void shouldCorrectDeletePerson() throws Exception {
        mvc.perform(delete("/persons/1"))
                .andExpect(status().isOk());
        verify(repository, times(1)).deleteById(1L);
    }


}