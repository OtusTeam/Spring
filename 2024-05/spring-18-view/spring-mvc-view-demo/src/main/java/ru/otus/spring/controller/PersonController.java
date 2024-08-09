package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Person;
import ru.otus.spring.dto.PersonDto;
import ru.otus.spring.repostory.PersonRepository;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Person> persons = repository.findAll();
        model.addAttribute("persons", persons);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("person", person);
        return "edit";
    }

    @PostMapping("/edit")
    public String savePerson(@Valid @ModelAttribute("person") PersonDto person,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        repository.save(person.toDomainObject());
        return "redirect:/";
    }
}
