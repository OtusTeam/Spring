package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.resposne.CommentBookResponseDto;
import ru.otus.spring.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @GetMapping("/getAllCommentsByBookId")
    public List<CommentBookResponseDto> getAllCommentsByBookId(@RequestParam("bookId") long bookId) {
        return commentService.getAllCommentsByBookId(bookId);
    }
}
