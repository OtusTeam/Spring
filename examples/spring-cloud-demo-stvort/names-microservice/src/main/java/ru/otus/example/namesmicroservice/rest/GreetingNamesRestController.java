package ru.otus.example.namesmicroservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class GreetingNamesRestController {

    private static final String[] NAMES = {"Юрий Громобоевич Бдыщ",
            "Говард Инванович Врумбельшпиц",
            "Лукас Люкятвойотецович Охничегожсебе",
            "Демосфен Менделеевич Иртыш", "Уга Чага"};

    @GetMapping("names/random")
    public String randomName(@Value("${default-form-of-appeal}") String defaultFormOfAppeal){
        Random rand = new Random();
        return defaultFormOfAppeal + " " + NAMES[rand.nextInt(NAMES.length)];
    }

}
