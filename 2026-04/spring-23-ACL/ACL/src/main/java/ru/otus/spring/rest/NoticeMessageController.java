package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.otus.spring.model.NoticeMessage;
import ru.otus.spring.repository.NoticeMessageRepository;
import ru.otus.spring.service.NoticeService;

import java.util.List;

@RestController
public class NoticeMessageController {

    private final NoticeService noticeService;

    public NoticeMessageController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/message")
    public List<NoticeMessage> getAll() {
        return noticeService.getAll();
    }

    @GetMapping("/message/{id}")
    public NoticeMessage get(@PathVariable("id") Integer id) {
        var result = noticeService.get( id );
        return result;
    }

    @PostMapping("/message")
    public NoticeMessage createMessage(@RequestBody NoticeMessage message) {
        return noticeService.create(message);
    }

    @PutMapping("/message/{id}")
    public NoticeMessage updateMessage(@PathVariable("id") Integer id, @RequestBody NoticeMessage message) {
        return noticeService.update(message);
    }
}
