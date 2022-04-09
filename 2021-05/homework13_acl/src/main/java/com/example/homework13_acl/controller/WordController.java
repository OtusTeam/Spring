package com.example.homework13_acl.controller;


import com.example.homework13_acl.dto.NewWordDto;
import com.example.homework13_acl.model.Dictionary;
import com.example.homework13_acl.model.Word;
import com.example.homework13_acl.model.WordState;
import com.example.homework13_acl.repository.DictionaryRepository;
import com.example.homework13_acl.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;


@Controller
public class WordController {

    private final WordRepository repository;
    private final DictionaryRepository dictRepository;


    @Autowired
    public WordController(WordRepository repository, DictionaryRepository dictRepository) {
        this.repository = repository;
        this.dictRepository = dictRepository;
    }

    @GetMapping("/words/all")
    public String dictionaryPage(Model model) {
        Dictionary dict = dictRepository.findById(1L).orElseThrow(NotFoundException::new);
        List<Word> dictWords = repository.findAllByDictionary(dict);
        model.addAttribute("dictionary", dict);
        model.addAttribute("words", dictWords);
        return "dict";
    }

    @GetMapping("words/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Word word = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("word", word);
        return "edit";
    }

    @GetMapping("/words/addWord")
    public String editNewPage(@RequestParam("id") long dictId, Model model) {
        NewWordDto wordForm = new NewWordDto();

        Dictionary dictionary = dictRepository.findById(dictId).orElseThrow(NotFoundException::new);

        model.addAttribute("wordForm", wordForm);
        model.addAttribute("dictionary", dictionary);
        return "add";
    }

    @PostMapping("/words/edit")
    public String saveWord(
            Word wordForm,
            Model model
    ) {
        Word word = repository.findById(wordForm.getId()).orElseThrow(NotFoundException::new);
        word.setTranslation(wordForm.getTranslation());
        word.setContext(wordForm.getContext());
        word.setExample(wordForm.getExample());
        Word saved = repository.save(word);
        model.addAttribute(saved);
        return "redirect:/words/all";
    }

    @PostMapping("/words/addWord")
    public String createWord(@RequestParam("dictId") long dictId, NewWordDto wordForm, Model model) {
        Word word = new Word();
        word.setName(wordForm.getName());
        word.setTranslation(wordForm.getTranslation());
        word.setContext(wordForm.getContext());
        word.setExample(wordForm.getExample());
        word.setAddedDate(LocalDate.now());
        word.setState(WordState.NEW);
        Dictionary dictionary = dictRepository.findById(dictId).orElseThrow(NotFoundException::new);
        word.setLearntPercent(0);
        word.setDictionary(dictionary);
        repository.save(word);
        return "redirect:/words/all";
    }

    @GetMapping("/words/delete")
    public String deleteWord(@RequestParam Long id) {

        repository.deleteById(id);
        return "redirect:/words/all";
    }

    @ExceptionHandler({NotFoundException.class})
    public ModelAndView handleNFE(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

}
