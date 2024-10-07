package ru.otus.testingdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.testingdemo.service.CityService;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final CityService cityService;

    @GetMapping("/")
    public String mainPage(Model model) {
        var cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "index";
    }
}
