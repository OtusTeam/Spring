package ru.otus.example.facadegateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {

    @GetMapping("/")
    public String mainPage(Model model, @Value("${greeting}") String greeting) {
        model.addAttribute("greeting", greeting);
        return "index";
    }
}
