package ru.otus.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRepository personRepository;

    private List<Person> persons = List.of(new Person(1L, "Vasya"), new Person(2L, "Dima"));

    @Test
    void shouldRenderListPageWithCorrectViewAndModelAttributes() throws Exception {
        when(personRepository.findAll()).thenReturn(persons);
        mvc.perform(get("/"))
                .andExpect(view().name("list"))
                .andExpect(model().attribute("persons", persons));
    }

    @Test
    void shouldRenderEditPageWithCorrectViewAndModelAttributes() throws Exception {
        when(personRepository.findById(1L)).thenReturn(Optional.of(persons.get(0)));
        mvc.perform(get("/edit").param("id", "1"))
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("person", persons.get(0)));
    }

    @Test
    void shouldRenderErrorPageWhenPersonNotFound() throws Exception {
        when(personRepository.findById(1L)).thenThrow(new NotFoundException());
        mvc.perform(get("/edit").param("id", "1"))
                .andExpect(view().name("customError"));
    }

    @Test
    void shouldSavePersonAndRedirectToContextPath() throws Exception {
        when(personRepository.findById(1L)).thenReturn(Optional.of(persons.get(0)));
        mvc.perform(post("/edit").param("id", "3").param("name", "Olya"))
                .andExpect(view().name("redirect:/"));
        verify(personRepository, times(1)).save(any(Person.class));
    }
}