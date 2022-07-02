package ru.otus.spring.microservice.rest;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.microservice.domain.Person;
import ru.otus.spring.microservice.repostory.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonsRestController {

    private final PersonRepository repository;

    public PersonsRestController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/persons")
    public ResponseEntity<?> findAllPersons() {
        List<Person> persons = repository.findAll();
        List<PersonResource> resources =  persons.stream().map(this::person2Resource)
                .collect(Collectors.toList());
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("api/persons/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
        Optional<Person> person = repository.findById(id);
        return new ResponseEntity<>(person2Resource(person.orElseThrow()), HttpStatus.OK);
    }

    private PersonResource person2Resource(Person person) {
        var resource = new PersonResource(person);
        resource.add(linkTo(methodOn(PersonsRestController.class).findById(person.getId())).withSelfRel());
        resource.add(linkTo(methodOn(PersonsRestController.class).findAllPersons()).withRel("all"));
        return resource;
    }

    private static class PersonResource extends RepresentationModel<PersonResource> {

        private final Person person;

        public PersonResource(Person person) {
            this.person = person;
        }

        public int getId() {
            return person.getId();
        }

        public String getName() {
            return person.getName();
        }
    }

}
