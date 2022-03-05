package com.example.homework12_security;

import com.example.homework12_security.controller.WordController;
import com.example.homework12_security.model.Dictionary;
import com.example.homework12_security.model.Word;
import com.example.homework12_security.repository.DictionaryRepository;
import com.example.homework12_security.repository.WordRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordController.class)
public class WordControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private WordRepository repository;

    @MockBean
    private DictionaryRepository dictRepository;

    @Test
    void shouldReturnCorrectWordsListByDict() throws Exception {
        Dictionary dict = new Dictionary();
        dict.setId(1L);
        dictRepository.save(dict);
        Word word1 = new Word(1, "word1", "translate1", dict);
        Word word2 = new Word(2, "word2", "translate2", dict);
        List<Word> words = List.of(word1, word2);
        repository.save(word1);
        repository.save(word2);
        given(repository.findAllByDictionary(dict)).willReturn(words);

        mvc.perform(get("/words/all"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteById() throws Exception{
        Dictionary dict = new Dictionary();
        Word word = new Word(1, "word1", "translate1", dict);
        given(repository.findById(1L)).willReturn(Optional.of(word));
        repository.deleteById(1L);
        given(repository.findById(1L)).willReturn(null);

        mvc.perform(get("/words/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection());

    }

}
