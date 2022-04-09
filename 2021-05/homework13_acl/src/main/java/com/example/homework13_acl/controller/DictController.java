package com.example.homework13_acl.controller;

import com.example.homework13_acl.model.Dictionary;
import com.example.homework13_acl.repository.DictionaryRepository;
import com.example.homework13_acl.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DictController {
    final DictionaryRepository repository;

    @Autowired
    public DictController(DictionaryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/dicts/all")
    public String dictionaryListPage(Model model) {

        List<Dictionary> dicts = repository.findAll();;
        model.addAttribute("dicts", dicts);
        return "dictList";
    }
}
