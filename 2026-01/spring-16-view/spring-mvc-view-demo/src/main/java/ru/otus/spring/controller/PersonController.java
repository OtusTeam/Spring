package ru.otus.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dto.PersonDto;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @GetMapping("/")
    public String listPage(Model model) {
        List<PersonDto> persons = repository.findAll().stream()
                .map(PersonDto::fromDomainObject).toList();
        model.addAttribute("persons", persons);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        PersonDto person = repository.findById(id)
                .map(PersonDto::fromDomainObject)
                .orElseThrow(NotFoundException::new);
        model.addAttribute("person", person);
        return "edit";
    }

    @PostMapping("/edit")
    public String savePerson(@Valid @ModelAttribute("person") PersonDto person,
                             BindingResult bindingResult,
                             @RequestParam(value = "hobby", defaultValue = "") List<String> hobby) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        log.debug("Hobby from plain RequestParam: {}", String.join(", ", hobby));
        log.debug("Hobby from DTO: {}", person.hobbyAsString());

        repository.save(person.toDomainObject());
        return "redirect:/";
    }
}
