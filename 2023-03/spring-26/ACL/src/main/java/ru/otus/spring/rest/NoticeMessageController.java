package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.NoticeMessage;
import ru.otus.spring.repository.NoticeMessageRepository;

import java.util.List;

@RestController
public class NoticeMessageController {

    private final NoticeMessageRepository repository;

    public NoticeMessageController(NoticeMessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/message")
    public List<NoticeMessage> getAll() {
        return repository.findAll();
    }

    @GetMapping("/message/{id}")
    public NoticeMessage getById(@PathVariable("id") Integer id) {
        return repository.getById(id);
    }

    @PutMapping("/message")
    public NoticeMessage getById(NoticeMessage message) {
        return repository.save(message);
    }
}
